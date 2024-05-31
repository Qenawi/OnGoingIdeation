**Additional Information:**

* The GIF image is loaded from an asset file using the `rememberAsyncImagePainter` composable.
* The animation of the dots is created using the `rememberInfiniteTransition` composable.
* The composables are arranged using the `Column`, `Row`, and `Box` composables.

## AnimatedDots Composable

The `AnimatedDots` composable creates an infinite animation of dots that appear and disappear.

## AnimatedLoading Composable

The `AnimatedLoading` composable displays the loading text message and the animated dots.

**Customization:**

You can customize the appearance of the loading screen by passing additional parameters to the
composables. You can also add additional child composables to the `Column` composable to provide
more information or functionality to the loading screen.