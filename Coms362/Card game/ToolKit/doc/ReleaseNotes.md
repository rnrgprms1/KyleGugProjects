# Cards Toolkit Release Notes

# 4/19/2020 
## PickupSP implementation, Better addToPile support, Remote Timer support
 
### We now support both PU52 and PU52MP. 
The single player implementation follows exactly the design documented in class notes. 

The classes changed or added to support PU52 are GameFactoryFactory, PickupInitCmd, P52SPRules, and P52SPGameFactory.  
 
### We now support setting a timer in the browser
The timer returns a (client-specified) event when the specified time expires. 

The Timer support includes a model class (Timer), a remoteEvent command (SetTimerRemote) and an inbound event (TimerEvent) to simplify use when only one timer is ever active. 

registerEvents() in the PickupRules has been enhanced to register TimerEvent for unmarshalling. The rulesDispatch hierarchy has also been modified to support dispatch of the new event. 

A modified version of pickup which uses the timer to block play for five seconds when the jack of clubs is "picked up." is available on branch "timer". If you are considering using the timer, we suggest you fetch and explore this branch. (If your working tree is committed, you can fetch and switch to a branch without changing any of your working code.) 
  

### More complete Remote Events
Prior releases have been using incomplete or partially implemented RemoteEvents extracted from an early prototype. We have completed several of the remote events, deprecated one, and added some new events to give better control how cards are inserted into a Pile or Deck. 

AddToPileRemote has been deprecated. It was incomplete, and would have duplicated the behavior of the new InsertAtPileTopRemote. 

RemoveFromPileRemote partial implementation has been improved. It now actually removes from the named pile. Create Pile now also has the desired effect in both the model and the browser.

### Remote Event to support War
Also new is InsertAtPileBottomRemote. This operation is necessary to meet the War requirements for handling pot sweeps. 

PickupInitCmd and PickupMove have been modified to use the new card/pile manipulation commands. 

### Other modified classes: 

- some obsolete code has been removed from  the InitGameEvent handler in PickupRules. 


### Testing: 

This push has passed these tests: 

- the example Junit tests. Your tests may be effected by the changes to RemoteEvents and the Move Commands. 
- manual testing of PU52 confirms single player startup. 
- manual testing of PU52MP with 1, 2, and 4 players confirms correct play in each configuration. 



### More Information about Changes

You can get a detailed view of all changes from eclipse  *BEFORE YOU MERGE* by right-clicking on the project and selecting compareWith > Branch, Tag, or Reference > then in the window that opens select remote tracking > origin/master. This is your MOST RELIABLE and fastest way to inventory prospective changes. Since you can browse the changes in any order and see them side by side, it is the preferred means of making sense of a new "push". 

A similar view is available from the Gitlab "History" tab. Using GitLab's HTML interface, you can walk through commits one by one before you attempt to merge. Each commit is accompanied by a commit comment that usually identifies the purpose of the commit. When dealing with a large codebase, in-line comments are generally not "browse-able" in a useful way and are often not complete nor up-to-date. Release notes are often more marketing oriented than technically oriented.  The source code control system generally offers a much more productive means of identifying and understanding changes.

