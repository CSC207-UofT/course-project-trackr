# Specification for the program

specification on google docs that's underlined: [specification](https://docs.google.com/document/d/1n7Uq_OP2d5JIQmQLOnWaaJ42P9wVLEaM4yTghBf5wIA/edit?usp=sharing)

A calendar in which a user can **create, edit, or delete** **birthday events** that correspond to certain dates and certain friends. 
Users can set a time before the **event** **to be notified**. Each **birthday event** should be able to **include personal comments and gift ideas**. 
Users should have the option to **look back** at previous events or be **reminded** of them in subsequent years.

## Example usage
```
$ trackr
> Welcome to TRACKR!                                                            

> Upcoming Birthdays:
> Mr Potato Head (2 days)
> John (13 days)
> Dave (21 days)
$ trackr add (create event) -b (event type) -d 2003/01/02 (date in specified format) -n Steve (name) -r 7 (reminder window)
> New birthday event created for Steve. You will get a reminder 7 days before their birthday.
$
```
