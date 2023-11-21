import Foundation
import SwiftUI
import Shared

struct DetailView: View {
    let viewModel = DetailViewModel(
        starWarsRepository: KoinDependencies().starWarsRepository
    )
    
    let objectName: String
    
    @State
    var object: StarWarsPlanet? = nil
    
    var body: some View {
        VStack {
            if let obj = object {
                ObjectDetails(obj: obj)
            }
        }.task {
            for await obj in viewModel.getObject(objectString: objectName) {
                object = obj!
            }
        }
    }
}

struct ObjectDetails: View {
    var obj: StarWarsPlanet
    
    var body: some View {
        ScrollView {
            
            VStack {
                
                VStack(alignment: .leading, spacing: 6) {
                    Text(obj.name)
                        .font(.title)
                }
                .padding(16)
            }
        }
    }
}

struct LabeledInfo: View {
    var label: String
    var data: String
    
    var body: some View {
        Spacer()
        Text("**\(label):** \(data)")
    }
}
