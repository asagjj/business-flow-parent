package com.lizo.busflow.station;


import com.lizo.busflow.bus.Bus;
import com.lizo.busflow.routing.Routing;

/**
 * 使用<bf:stop>标签定义类
 *
 * 代理模式，代理station
 * Created by lizhou on 2017/3/14/014.
 */
public class StationRoutingWrap implements Station {
    private Station station;
    private Routing routing;

    public void doBusiness(Bus bus) {
        if (station != null) {
            try {
                bus.arrive(station);
            } catch (Exception e) {
                bus.occurException(e);
            }
        }
        if (routing != null) {
            Station next = routing.doRouting(bus.getBusContext());
            next.doBusiness(bus);
        }
    }

    public String getName() {
        return station.getName();
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public Routing getRouting() {
        return routing;
    }

    public void setRouting(Routing routing) {
        this.routing = routing;
    }


}