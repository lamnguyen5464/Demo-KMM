import SwiftUI
import shared

struct ContentView: View {
    let greet = Greeting().greeting()
    
    let test = testDemo()
 
    var body: some View {
        Text(greet)
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}

func testDemo() {
    let apiRequest = CatFactApi()
    
    print(apiRequest.getFromCache().fact)
    
    apiRequest.getCatFact(completionHandler: { res, err in
        print(res?.fact)
    })
}
 
