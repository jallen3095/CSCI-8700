sig Omahelp {
		usrs: UserList, evts: EventList, organizations: set Organization
	}

sig Event {
		attendees: UserList, interestedUsers: UserList, organizers: UserList, name: lone Name, desc: lone Description, date: lone Date, addr: lone Address
	}

sig EventList {
		known: Id, events: known -> one Event
	}

sig UserList {
		known: Id, users: known -> one User
	}

sig Id, User, Organization, Address, Name, Description, Date {}


pred SetEventName [ev', ev: Event, n: Name] {
		ev' = ev
		ev'.name = n
	}

pred SetEventDesc [ev', ev: Event, d: Description] {
		ev' = ev
		ev'.desc = d
	}

pred SetEventAddr [ev', ev: Event, a: Address] {
		ev' = ev
		ev'.addr = a
	}

pred SetEventDate [ev', ev: Event, d: Date] {
		ev' = ev
		ev'.date = d
	}

pred FindUser [li: UserList, id: Id, user: lone User] {
    		user = li.users[id]
    	}

pred AddUser [li', li: UserList, id: Id, user: User] {
		li'.users = li.users ++ (id -> user)
	}

pred DeleteUser [li', li: UserList, id: Id] {
    		li'.users = li.users - (id->User)
    	}

pred FindEvent [oh: Omahelp, id: Id, event: lone Event] {
    		event = oh.evts.events[id]
    	}

pred AddEvent [oh', oh: Omahelp, id: Id, newEvent: Event] {
		oh' = oh
		oh'.evts.events = oh'.evts.events ++ (id -> newEvent)
	}


pred EditEvent [oh', oh: Omahelp, id: Id, newEvent: Event] {
		oh' = oh
		oh'.evts.events[id] = newEvent
	}


pred DeleteEvent [oh, oh': Omahelp, id: Id] {
		oh' = oh
    		oh'.evts.events = oh.evts.events - (id->Event)
    	}

assert SetEventNameWorks {
		all ev',ev: Event, n: Name |
			SetEventName [ev',ev,n] => ev'.name = n
	}

assert SetEventDescWorks {
		all ev',ev: Event, d: Description |
			SetEventDesc [ev',ev,d] => ev'.desc = d
	}

assert SetEventAddrWorks {
		all ev',ev: Event, a: Address |
			SetEventAddr [ev',ev,a] => ev'.addr = a
	}

assert SetEventDateWorks {
		all ev',ev: Event, d: Date |
			SetEventDate [ev',ev,d] => ev'.date = d
	}

assert AddUserWorks {
    		all li', li: UserList, id: Id, user: User, user': lone User |
       			AddUser [li',li,id,user] && FindUser [li',id,user'] => user = user'
    	}

assert DeleteUserWorks {
    		all li1,li2,li3: UserList, id: Id, user: User|
        		AddUser [li1,li2,id,user] && DeleteUser [li2,li3,id]
          		  => li1 = li3
    	}

assert AddEventWorks {
    		all oh', oh: Omahelp, id: Id, event: Event, event': lone Event |
       			AddEvent [oh',oh,id,event] && FindEvent [oh,id,event'] => event = event'
    	}

assert EditEventWorks {
    		all oh', oh: Omahelp, id: Id, event, event2: Event, event': lone Event |
       			AddEvent [oh',oh,id,event] && EditEvent[oh',oh,id,event2] && FindEvent [oh,id,event'] => event2 = event' && not event = event'
    	}

assert DeleteEventWorks {
    		all oh1,oh2,oh3: Omahelp, id: Id, event: Event|
        		AddEvent [oh1,oh2,id,event] && DeleteEvent [oh2,oh3,id]
          		  => oh1.evts = oh3.evts
    	}

check SetEventNameWorks for 10
check SetEventDescWorks for 10
check SetEventAddrWorks for 10
check SetEventDateWorks for 10
check AddUserWorks for 10
check DeleteUserWorks for 10
check AddEventWorks for 10
check EditEventWorks for 10
check DeleteEventWorks for 10
