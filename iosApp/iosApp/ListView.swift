import SwiftUI
import KMMViewModelSwiftUI
import Shared

struct ListView: View {
    let viewModel = ListViewModel(
        starWarsRepository: KoinDependencies().starWarsRepository
    )
    
    @State
    var objects: [StarWarsPlanet] = []
    
    let columns = [
        GridItem(.adaptive(minimum: 120), alignment: .top)
    ]
    
    var body: some View {
        ZStack {
            if !objects.isEmpty {
                NavigationStack {
                    ScrollView {
                        LazyVGrid(columns: columns, alignment: .leading, spacing: 20) {
                            ForEach(objects, id: \.name) { item in
                                NavigationLink(destination: DetailView(objectName: item.name)) {
                                    ObjectFrame(obj: item, onClick: {})
                                }
                                .buttonStyle(PlainButtonStyle())
                            }
                        }
                        .padding(.horizontal)
                    }
                }
            } else {
                Text("No data available")
            }
        }.task {
            for await objs in viewModel.objects {
                objects = objs
            }
        }
    }
}

struct ObjectFrame: View {
    let obj: StarWarsPlanet
    let onClick: () -> Void
    
    var body: some View {
        VStack(alignment: .leading, spacing: 4) {
            Text(obj.name)
                .font(.headline)
            
            Text(obj.orbital_period)
                .font(.subheadline)
            
            Text(obj.gravity)
                .font(.caption)
        }
    }
}
