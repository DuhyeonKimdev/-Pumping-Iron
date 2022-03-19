package com.fp.pi.calendar;

import java.util.List;

public interface CalendarMapper {

	int recordRoutine(RoutineBean r);

	List<RoutineBean> showRoutine(RoutineBean r);

	int delRoutine(RoutineBean r);
	
	/////////////////////////////////////////////////////
	
	int regSchedule(ContestBean c);
	
	List<ContestBean> contestSchedule();

	int delSchedule(ContestBean c);

	List<ContestDetailBean> detailSchedule(ContestDetailBean cd);

	List<ContestDetailBean> detailSchedule2();

}
