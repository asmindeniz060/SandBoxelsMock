# SandBoxelsMock
Interactive Falling Sand simulation built with Java Swing. Features sand, water, smoke, seeds, and procedurally growing cactus particles using a randomized multi-pass cellular automaton update system.
# Falling Sand Simulation with Cactus Growth

An interactive 2D cellular automaton sandbox inspired by classic *Falling Sand* games. The simulation models simple physical rules (gravity, buoyancy, spreading) that lead to emergent behavior such as piling sand, flowing water, drifting smoke, and procedurally growing cacti.

This project was developed as part of a cellular automata / object-oriented programming assignment.

---

## Features

### Core Simulation

* **Grid-based world** where each cell is either empty or contains a particle
* **Multi-pass randomized update algorithm** to avoid directional bias and simulate near-simultaneous motion
* **Mouse-based interaction**: click and drag to add or remove particles within a circular brush radius
* **Manual step** and **automatic timer-based updates** (every 35 ms using `javax.swing.Timer`)

### Implemented Particle Types

#### Stone

* Completely stationary
* Cannot be displaced by any other particle

#### Sand

* Affected by gravity
* Falls straight down when possible
* Slides diagonally down-left or down-right when blocked (randomized order)
* Can displace lighter particles (e.g., water, smoke)

#### Water

* Affected by gravity and flows to find its level
* Falls down or diagonally when possible
* Spreads horizontally when blocked
* Maintains a preferred horizontal direction that flips when blocked

#### Smoke

* Affected by buoyancy (rises upward)
* Drifts diagonally and horizontally when blocked
* Has a finite lifetime (100 steps)
* Gradually fades in color as it ages
* Uses probabilistic rules for rising and spreading based on age

#### Seed 

* Can be placed by the user
* Affected by gravity
* Disappears if it lands on a non-sand particle
* Transforms into a **Cactus** when coming to rest on top of sand

#### Cactus 

* Stationary and immovable
* Grows procedurally over time
* Each cactus has a **growth level** (maximum: 8)
* Attempts to grow every **10 update steps**

**Growth Rules:**

* 80% chance to grow upward into an empty cell
* If upward growth fails:

  * 10% chance to grow left
  * 10% chance to grow right
* Growth only occurs if the target cell is empty
* Growth is blocked if **two or more cactus particles** exist in the surrounding cells of the target position (above, above-left, above-right, left, right)
* Newly created cactus particles inherit a growth level one higher than the parent

---

## Update Algorithm

Each update step follows this process:

1. Reset all particles to an *unupdated* state
2. Perform **8 randomized passes** over the grid
3. For each particle encountered:

   * If it has not yet been updated, apply its movement or behavior rules
   * If it moves or displaces another particle, mark it as updated

This approach minimizes artifacts caused by sequential updates and produces more natural motion.

---

## User Interface

* Grid visualization with color-coded particles
* Buttons to:

  * Select particle type (Stone, Sand, Water, Smoke, Seed, Erase)
  * Step the simulation once
  * Toggle automatic simulation updates
* Mouse interaction:

  * Click or drag to place particles
  * Circular brush radius for smoother placement

The UI is implemented using **Java Swing**, including `MouseListener`, `MouseMotionListener`, and `Timer`.

---

## Object-Oriented Design

* Each particle type is implemented as a separate class
* Shared behavior is handled through inheritance and polymorphism
* The world/grid logic is decoupled from particle behavior

This structure makes it easy to add new particle types and behaviors.

---

