# WordamentBot

A java bot that automatically plays and consistently wins at Microsoft Wordament.


## Prerequires

1. Git 2.6+
2. Maven 3.3+
3. Java 8+
4. Android emulator (NoxPlayer) scaled and positioned appropriated on screen (Top,Left) = (0,0)
5. Wordament App Installed on the emulator


## How to Play

Clone

```
git clone https://github.com/humbertodias/java-bot-wordament.git
```

Inside the folder

```
cd java-bot-wordament
```

Run

```
mvn package exec:java -Dexec.mainClass="bot.wordament.Main"
```

## Output
![Preview](doc/output.gif)


## References


1. [Wordament PlayStore](https://play.google.com/store/apps/details?id=com.microsoft.wordament&hl=pt_BR)

2. [Nox Android Player](https://pt.yeshen.com)
