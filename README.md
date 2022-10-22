# 3D-Model-DSL
A Kotlin DSL for making 3d models for [the site](https://tumbusam.ru/ "Тумбу сам").  
Examples can be found in models module.
## Example
Following kotlin code
```kotlin
model("cabinet1", "Cabinet Name") {
    val mainMaterial = Material("mlMain", "Material", "ДСП", 48, 15, "/Img/dsp.jpg") { 84 }
    val width = Property("sh", "Main width", 2000, 3000) { 2300 }
    
    val materialWidth = 18.toProperty()
   
    // Element name
    element(mainMaterial) {
        x = 10.toProperty()
        y = 0
        // z = 0 isn't neccessary
        dx = materialWidth
        dy = width
        dz = 2 * width
    }
}
```
will generate following xml file
```xml
<M Name="Шкаф непонятный">

  <S id="mlMain" Name="Material" Ml="ДСП" kr="48" km="15" t="/Img/dsp.jpg" >84</S>

  <S id="sh" Name="Main width" min="2000" max="3000" step="1" >2300</S>
  
  <E m= "mlMain" kr="15">
    <S tip="dx">18</S>
    <S tip="dy">sh</S>
    <S tip="dz">2*sh</S>
    <S tip="x">10</S>
    <S tip="y">0</S>
    <S tip="z">0</S>
  </E>
</M>
```
## Future goals
- [ ] Add expression simplifier
- [ ] Fix naming issues
- [ ] Add more fuctionality
