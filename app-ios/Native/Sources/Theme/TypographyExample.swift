import SwiftUI

// MARK: - Typography Usage Examples
struct TypographyExampleView: View {
    var body: some View {
        ScrollView {
            VStack(alignment: .leading, spacing: 24) {
                // MARK: - Display Examples
                Group {
                    Text("Display Large")
                        .font(Typography.displayLarge)
                    
                    Text("Display Medium")
                        .font(Typography.displayMedium)
                    
                    Text("Display Small")
                        .font(Typography.displaySmall)
                }
                
                Divider()
                
                // MARK: - Headline Examples
                Group {
                    Text("Headline Large")
                        .font(Typography.headlineLarge)
                    
                    Text("Headline Medium")
                        .font(Typography.headlineMedium)
                    
                    Text("Headline Small")
                        .font(Typography.headlineSmall)
                }
                
                Divider()
                
                // MARK: - Title Examples
                Group {
                    Text("Title Large")
                        .font(Typography.titleLarge)
                    
                    Text("Title Medium")
                        .font(Typography.titleMedium)
                    
                    Text("Title Small")
                        .font(Typography.titleSmall)
                }
                
                Divider()
                
                // MARK: - Label Examples
                Group {
                    Text("Label Large")
                        .font(Typography.labelLarge)
                    
                    Text("Label Medium")
                        .font(Typography.labelMedium)
                    
                    Text("Label Small")
                        .font(Typography.labelSmall)
                }
                
                Divider()
                
                // MARK: - Body Examples
                Group {
                    Text("Body Large - This is a sample text to demonstrate the body large typography style.")
                        .font(Typography.bodyLarge)
                    
                    Text("Body Medium - This is a sample text to demonstrate the body medium typography style.")
                        .font(Typography.bodyMedium)
                    
                    Text("Body Small - This is a sample text to demonstrate the body small typography style.")
                        .font(Typography.bodySmall)
                }
                
                Divider()
                
                // MARK: - Using Typography Style with Line Height
                Group {
                    Text("Typography Style Example")
                        .typographyStyle(.headlineLarge)
                    
                    Text("This example uses the typography style modifier which includes font, line height, and letter spacing.")
                        .typographyStyle(.bodyLarge)
                }
            }
            .padding()
        }
    }
}

// MARK: - Preview
#Preview {
    TypographyExampleView()
}