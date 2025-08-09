package io.github.droidkaigi.confsched.data.staff

import de.jensklingenberg.ktorfit.Ktorfit
import de.jensklingenberg.ktorfit.http.GET
import dev.zacsweers.metro.ContributesBinding
import dev.zacsweers.metro.Inject
import io.github.droidkaigi.confsched.data.DataScope
import io.github.droidkaigi.confsched.data.staff.response.StaffItemResponse
import io.github.droidkaigi.confsched.data.staff.response.StaffResponse
import io.github.droidkaigi.confsched.model.staff.Staff
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList

public interface StaffApi {
    @GET("staff")
    public suspend fun getStaff(): StaffResponse
}

@ContributesBinding(DataScope::class)
@Inject
public class DefaultStaffApiClient(
    ktorfit: Ktorfit,
) : StaffApiClient {
    private val api = ktorfit.create<StaffApi>()

    override suspend fun staff(): PersistentList<Staff> {
        val response = api.getStaff()
        return response.staff.map { it.toStaff() }.toPersistentList()
    }
}

private fun StaffItemResponse.toStaff(): Staff {
    return Staff(
        id = id,
        name = name,
        icon = icon,
        profileUrl = profileUrl,
    )
}