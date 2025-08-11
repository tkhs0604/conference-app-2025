import Dependencies
import Model
import shared
import Theme

struct RootPresenter {
    @MainActor func prepareWindow() {
        Fonts.register()
        
        prepareDependencies {
            KMPDependencies.prepareKMPDependencies(&$0)
        }
    }
}
