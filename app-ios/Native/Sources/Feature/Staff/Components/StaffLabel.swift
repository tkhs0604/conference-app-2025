import SwiftUI

struct StaffLabel: View {
    let staff: Staff
    
    var body: some View {
        HStack(spacing: 16) {
            // TODO: Replace with actual staff avatar when available
            Image(systemName: "person.circle.fill")
                .resizable()
                .frame(width: 56, height: 56)
                .foregroundColor(.secondary)
            
            VStack(alignment: .leading, spacing: 4) {
                Text(staff.name)
                    .font(.body)
                    .fontWeight(.medium)
                    .foregroundColor(.primary)
                
                if let role = staff.role {
                    Text(role)
                        .font(.caption)
                        .foregroundColor(.secondary)
                }
                
                Text("@\(staff.githubUsername)")
                    .font(.caption2)
                    .foregroundColor(.accentColor)
            }
            
            Spacer()
            
            Image(systemName: "chevron.right")
                .font(.caption)
                .foregroundColor(.secondary)
        }
        .padding(.vertical, 12)
        .contentShape(Rectangle())
    }
}