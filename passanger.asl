// Agent passanger

+start <- move_towards(2,1).
+pos(1,1)<- .wait(500);move_towards(2,1).

// todo jegyellenorzes
+pos(2,1) <- view_ticket.
+ticket_ok <- move_towards(6,1).
	
+pos(X,1):X>=3 & X<=5
	<- move_towards(6,1).
// todo femdetektor	
+pos(6,1) <- .wait(1000);move_towards(7,1).
// todo femdetektor	
+pos(7,1) <- .wait(1000);move_towards(8,1).
// todo femdetektor
+pos(X,1):X>=8
	<- move_towards(14,1).
