sig Omahelp {
    users: set User, eventIds: set Id,  events: eventIds -> one Event, organizations: set Organization
}

sig Event {
    attIds, intIds, orgIds: set Id, attendees: attIds ->one User, interestedUsers: intIds ->one User, organizers: orgIds ->one User, name: lone Name, desc: lone Description, date: lone Date, addr: lone Address
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

pred FindUser [li: set Id -> one User, id: Id, user: lone User] {
    		user = li[id]
    	}

pred AddUser [li', li: set Id -> one User, id: Id, user: User] {
		li' = li ++ (id -> user)
	}

pred DeleteUser [li', li: set Id -> one User, id: Id] {
    		li' = li - (id->User)
    	}

pred FindEvent [oh: Omahelp, id: Id, event: lone Event] {
    		event = oh.events[id]
    	}

pred AddEvent [oh', oh: Omahelp, id: Id, newEvent: Event] {
		oh' = oh
		oh'.events = oh'.events ++ (id -> newEvent)
	}


pred EditEvent [oh', oh: Omahelp, id: Id, newEvent: Event] {
		oh' = oh
		oh'.events [id] = newEvent
	}


pred DeleteEvent [oh, oh': Omahelp, id: Id] {
    		oh'.events = oh.events - (id->Event)
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
    		all li', li: set Id -> one User, id: Id, user: User, user': lone User |
       			AddUser [li',li,id,user] && FindUser [li',id,user'] => user = user'
    	}

assert DeleteUserWorks {
    		all li1,li2,li3: set Id -> one User, id: Id, user: User|
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
          		  => oh1.events = oh3.events
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
