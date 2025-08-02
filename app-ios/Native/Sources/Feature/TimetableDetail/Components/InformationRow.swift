import SwiftUI
import Theme

struct InformationRow: View {
    let icon: Image
    let title: String
    let titleColor: Color
    let content: String

    var body: some View {
        HStack {
            icon
                .renderingMode(.template)
                .foregroundStyle(titleColor)
            HStack(spacing: 12) {
                Text(title)
                    .font(.system(size: 12, weight: .bold))
                    .foregroundStyle(titleColor)
                HStack {
                    Text(content)
                        .font(.system(size: 14))
                        .foregroundStyle(.primary)
                }
            }
        }
        .frame(maxWidth: .infinity, alignment: .leading)
    }
}

#Preview {
    InformationRow(
        icon: Image(systemName: "clock"),
        title: "日時",
        titleColor: .blue,
        content: "2024.09.14 / 11:20 ~ 12:00"
    )
}