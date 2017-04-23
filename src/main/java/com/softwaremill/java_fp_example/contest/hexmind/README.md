# Road to a more functional Java
## Example refactoring

### Prologue

> The Open Graph protocol enables any web page to become a rich object in a social graph.
> og:image - An image URL which should represent your object within the graph.
> 
> http://ogp.me/


## Who is who?

### [FacebookImage](FacebookImage.java)

client-specific *OpenGraphPage* image

```
String img = FacebookImage.fromPage(postUrl).extractUrl()
```

### [OpenGraphPage](OpenGraphPage.java) 

non-functional OG node: errors handling and *OpenGraphExtractor* as internal implementation

```
public String extractMetaContent() {
    return new OpenGraphExtractor(options)
        .extractContent()
        .onFailure(this::onFailure)
        .getOrElse(options::getFallbackContent);
}
```

### [OpenGraphExtractor](OpenGraphExtractor.java) 

functional OG node metadata: extraction without side effects

```
Try<String> extractContent() {
    return Try.of(this::loadWebPage)
        .map(this::getMetaElements)
        .flatMap(this::findContent);
}
```
