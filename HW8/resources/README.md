# Design

The main of my design is to use a canvas to represent the moment in time before taking a snapshot.
The analogy is when you take a screenshot on your computer, that represents the canvas,
once you save the screenshot, then a new snapshot is created, that new snapshot is created and stored in the album.

# Technical Decisions and Reasons

## Canvas layer
- Abstract the manipulation of shapes on this layer to avoid bloating Album level to do manipulations

## Interfaces for shapes and canvas
- Provide a blueprint for concrete classes 
- Extensibility for future extensions
- Following Open-closed Principle (OCP):
  - Objects or entities should be open for extension but closed for modification.

## Abstract shape
- Avoid repetition of code
- Shape concrete classes are clean and easy to read

## `id` variable for shape 
- Assumed `name` variable can be duplicate
- Unique identifier to check same shapes with same properties, but different `id`


## `createSnapshot` adds to `snapshotList`
- Did not separate the creation and addition to the list of snapshots as snapshots will always 
have unique ids due to the timestamps at the moment of creation

# HW 9 

# How to run

# Design Change from HW8


HTML addition of `name` attribute
Using name for backend purposes as a best practice to send data to server
`https://www.w3docs.com/snippets/html/what-is-the-difference-between-the-id-and-name-attributes.html`

# Additions

- Creation of `canvas` in `Album` constructor
  - commit `85c66e9`

# Bug fixes

- Missed addition to `shapeList` in canvas after creation
  - commit `ed9eb86`

