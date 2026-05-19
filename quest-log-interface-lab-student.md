# Quest Log Interface Lab

**Estimated time:** about 2 hours  
**Mode:** mostly solo; 5–10 minute partner discussion at the beginning and end  
**Concept focus:** interface design, interface-based variables, interface-based collections, and class responsibility

## Scenario

You are building a small console application for a fantasy adventure game. During a quest, many different things can happen: a player might defeat a monster, find treasure, trigger a trap, or discover a hidden room.

These things are not the same kind of object, but they all have one shared behavior: each one affects the player’s quest score.

Your job is to design the app so the `QuestLog` can work with any score-affecting event without knowing the exact class of that event.

## What you are practicing

By the end of this lab, you should be able to explain:

- Why an interface is useful when different classes share a behavior.
- Why `List<ScoreEvent>` is more flexible than `List<MonsterDefeat>`.
- Why the `QuestLog` should not contain a large `if/else` or `switch` statement checking every event type.
- How adding a new event class should require little or no change to existing classes.

---

## Part 0: Notice the design problem

Before coding, look at this design:

```text
QuestLog
---------------------------------
- monsterDefeats: List<MonsterDefeat>
- treasuresFound: List<TreasureFound>
- trapsTriggered: List<TrapTriggered>
- roomsExplored: List<RoomExplored>
---------------------------------
+ addMonsterDefeat(...)
+ addTreasureFound(...)
+ addTrapTriggered(...)
+ addRoomExplored(...)
+ getTotalScore()
```

This design works, but it has a problem. Every time the game adds a new kind of score event, the `QuestLog` class must change.

Now look at the target design:

```text
              <<interface>>
               ScoreEvent
        -------------------------
        + getName(): String
        + getPoints(): int
        + getSummary(): String
        -------------------------
             ^       ^       ^
             |       |       |
 MonsterDefeat  TreasureFound  TrapTriggered  RoomExplored

QuestLog
---------------------------------
- events: List<ScoreEvent>
---------------------------------
+ addEvent(event: ScoreEvent): void
+ getTotalScore(): int
+ getHighestScoringEvent(): ScoreEvent
+ getLowestScoringEvent(): ScoreEvent
+ printSummary(): void
```

The `QuestLog` now depends on the interface, not on each concrete class.

---

## Part 1: Create the first two concrete classes

Create a new Java project named `quest-log-interface-lab`.

Create these classes:

### `MonsterDefeat`

Fields:

```java
private String monsterName;
private int difficultyLevel; // 1 to 5
private boolean boss;
```

Methods:

```java
public String getName()
public int getPoints()
public String getSummary()
```

Scoring rule:

```text
points = difficultyLevel * 50
if boss is true, add 100 more points
```

Example summary:

```text
Defeated Goblin Captain for 150 points
```

### `TreasureFound`

Fields:

```java
private String treasureName;
private String rarity; // common, rare, or legendary
```

Methods:

```java
public String getName()
public int getPoints()
public String getSummary()
```

Scoring rule:

```text
common = 25 points
rare = 75 points
legendary = 150 points
anything else = 0 points
```

Example summary:

```text
Found rare Sapphire for 75 points
```

### Quick test

In `Program.java`, create one `MonsterDefeat` and one `TreasureFound`. Print their summaries and point values.

At this point, you should notice something: both classes have the same public methods, but Java does not yet know that they follow the same contract.

---

## Part 2: Add the interface

Create an interface named `ScoreEvent`:

```java
public interface ScoreEvent {
    String getName();
    int getPoints();
    String getSummary();
}
```

Update `MonsterDefeat` and `TreasureFound` so that they implement `ScoreEvent`.

```java
public class MonsterDefeat implements ScoreEvent {
    // class code here
}
```

```java
public class TreasureFound implements ScoreEvent {
    // class code here
}
```

Add the `@Override` annotation above each method required by the interface.

### Quick test

In `Program.java`, create variables using the interface type:

```java
ScoreEvent event1 = new MonsterDefeat("Goblin Captain", 3, false);
ScoreEvent event2 = new TreasureFound("Sapphire", "rare");
```

Print both summaries.

Think about this question:

```text
Why can both variables be declared as ScoreEvent even though the objects are different classes?
```

---

## Part 3: Create the `QuestLog`

Create a `QuestLog` class.

Fields:

```java
private List<ScoreEvent> events;
```

Constructor:

```java
public QuestLog() {
    events = new ArrayList<>();
}
```

Methods:

```java
public void addEvent(ScoreEvent event)
public int getTotalScore()
public ScoreEvent getHighestScoringEvent()
public ScoreEvent getLowestScoringEvent()
public void printSummary()
```

Rules:

- `addEvent()` adds the event to the list.
- `getTotalScore()` loops through the list and adds all event point values.
- `getHighestScoringEvent()` returns the event with the highest point value.
- `getLowestScoringEvent()` returns the event with the lowest point value.
- If the list is empty, highest and lowest should return `null`.
- `printSummary()` displays every event summary, then displays the total score.

Important design rule:

```text
QuestLog should not use instanceof to check for MonsterDefeat or TreasureFound.
QuestLog should only call methods from the ScoreEvent interface.
```

### Required output style

Your exact text can vary, but your summary should look something like this:

```text
QUEST LOG
-----------------------------
1. Defeated Goblin Captain for 150 points
2. Found rare Sapphire for 75 points
3. Triggered Spike Trap for -30 points
4. Explored Ancient Library for 60 points
-----------------------------
Total score: 255
Best event: Defeated Goblin Captain for 150 points
Worst event: Triggered Spike Trap for -30 points
```

---

## Part 4: Add two more event classes

Create two more classes that implement `ScoreEvent`.

### `TrapTriggered`

Fields:

```java
private String trapName;
private int damage;
```

Scoring rule:

```text
points = damage * -10
```

Example:

```text
Triggered Spike Trap for -30 points
```

### `RoomExplored`

Fields:

```java
private String roomName;
private boolean secretFound;
```

Scoring rule:

```text
base points = 20
if secretFound is true, add 40 more points
```

Example:

```text
Explored Ancient Library for 60 points
```

### Design checkpoint

After adding these classes, you should be able to add them to the existing `QuestLog` without changing the `QuestLog` fields or total-score logic.

In `Program.java`, create a `QuestLog`, add at least six events, and print the quest summary.

---

## Part 5: Simple menu challenge

After your hard-coded test works, add a small menu that lets the user add events while the program is running.

```text
1) Add monster defeat
2) Add treasure found
3) Add trap triggered
4) Add room explored
5) View quest log
0) Quit
```

Keep the menu simple. The main goal is not fancy input handling. The main goal is that each menu option creates the correct object and adds it to the `QuestLog` using this method:

```java
questLog.addEvent(event);
```

---

## Finished requirements

Your project is complete when:

- `ScoreEvent` is an interface.
- At least four classes implement `ScoreEvent`.
- `QuestLog` stores events in a `List<ScoreEvent>`.
- `QuestLog` calculates total score without knowing the concrete event classes.
- Adding a new event class does not require changing `QuestLog`.
- The app prints a clear quest summary.

---

## Reflection questions

Answer these in comments at the bottom of `Program.java` or in a short `README.md`.

1. What problem did the `ScoreEvent` interface solve?
2. Why is `QuestLog` better with `List<ScoreEvent>` than with separate lists for each event type?
3. What code had to change when you added `TrapTriggered` and `RoomExplored`?
4. What code did not have to change?
5. Would an abstract class have been better here? Why or why not?

---
