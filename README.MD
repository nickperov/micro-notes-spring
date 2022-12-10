# Micro notes project

### Description

This project experimental project - to build minimal spring REST application.

### Build

mvn clean package

### Run

java -jar target/micro-notes-t1.jar

### APIs

**notes-controller**

|                                                           |                     |                       |
|-----------------------------------------------------------|---------------------|-----------------------|
| <font style="background-color:#4379a8;">**GET**</font>    | /api/notes          | list all notes        |
| <font style="background-color:#4379a8;">**GET**</font>    | /api/notes/{noteId} | get single note       |
| <font style="background-color:#6e8641;">**POST**</font>   | /api/notes          | create note           |
| <font style="background-color:#ffd541;">**PUT**</font>    | /api/notes/{noteId} | create or update note |
| <font style="background-color:#b22222;">**DELETE**</font> | /api/notes/{noteId} | delete note           |

    noteId is UUID
    note body is plain string

### Authors

Nikolay Perov

### License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

