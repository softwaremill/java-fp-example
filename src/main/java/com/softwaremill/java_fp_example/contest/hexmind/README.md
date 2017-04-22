# Road to a more functional Java
## Example refactoring

```
The Open Graph protocol enables any web page to become a rich object in a social graph.
og:image - An image URL which should represent your object within the graph.

http://ogp.me/
```

### Who is who?

[FacebookImage](FacebookImage.java)
client-specific *OpenGraphPage* image
> FacebookImage.fromPage(postUrl).extractUrl()

[OpenGraphPage](OpenGraphPage.java) 
non-functional OG node: handles errors and uses *OpenGraphExtractor* as implementation
> public String extractMetaContent() {
>     return new OpenGraphExtractor(options)
>         .[extractContent](OpenGraphExtractor.java)()
>         .onFailure(this::onFailure)
>         .getOrElse(options::getFallbackContent);
> }

[OpenGraphExtractor](OpenGraphExtractor.java) 
functional OG node metadata extraction without side effects
> Try<String> extractContent() {
>     return Try.of(this::loadWebPage)
>         .map(this::getMetaElements)
>         .flatMap(this::[findContent](OpenGraphExtractor.java));
> }