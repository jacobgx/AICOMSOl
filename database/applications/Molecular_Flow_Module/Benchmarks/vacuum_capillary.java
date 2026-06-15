/*
 * vacuum_capillary.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:17 by COMSOL 6.3.0.290. */
public class vacuum_capillary {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Molecular_Flow_Module\\Benchmarks");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("fmf", "FreeMolecularFlow", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/fmf", true);

    model.param().set("Lc", "2[mm]");
    model.param().descr("Lc", "\u6bdb\u7ec6\u7ba1\u957f\u5ea6");
    model.param().set("Rc", "0.2[mm]");
    model.param().descr("Rc", "\u6bdb\u7ec6\u7ba1\u534a\u5f84");
    model.param().set("p0", "1e-3[mbar]");
    model.param().descr("p0", "\u8d2e\u69fd\u538b\u529b");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"Rc", "Lc"});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("fmf").create("tv1", "TotalVacuum", 1);
    model.component("comp1").physics("fmf").feature("tv1").selection().set(3);
    model.component("comp1").physics("fmf").create("res1", "Reservoir", 1);
    model.component("comp1").physics("fmf").feature("res1").selection().set(2);
    model.component("comp1").physics("fmf").feature("res1").setIndex("p0", "p0", 0);

    model.component("comp1").func().create("int1", "Interpolation");
    model.component("comp1").func("int1").setIndex("table", 2, 0, 0);
    model.component("comp1").func("int1").setIndex("table", 0.5142, 0, 1);
    model.component("comp1").func("int1").setIndex("table", 4, 1, 0);
    model.component("comp1").func("int1").setIndex("table", 0.3566, 1, 1);
    model.component("comp1").func("int1").setIndex("table", 6, 2, 0);
    model.component("comp1").func("int1").setIndex("table", 0.2755, 2, 1);
    model.component("comp1").func("int1").setIndex("table", 8, 3, 0);
    model.component("comp1").func("int1").setIndex("table", 0.2253, 3, 1);
    model.component("comp1").func("int1").setIndex("table", 10, 4, 0);
    model.component("comp1").func("int1").setIndex("table", "0.1910", 4, 1);

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop1").selection().set(2);
    model.component("comp1").cpl("intop1").set("axisym", false);
    model.component("comp1").cpl().create("intop2", "Integration");
    model.component("comp1").cpl("intop2").set("axisym", true);
    model.component("comp1").cpl("intop2").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop2").selection().set(3);
    model.component("comp1").cpl("intop2").set("axisym", false);

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(2, 3);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 15);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(1, 4);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("numelem", 60);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "Lc", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "Lc", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("plistarr", "range(0.4[mm],0.4[mm],2[mm])", 0);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").set("data", "none");
    model.result().dataset("rev1").set("startangle", 270);
    model.result().dataset("rev1").set("revangle", 270);
    model.result().dataset("rev1").set("data", "dset2");
    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u5165\u5c04\u5206\u5b50\u901a\u91cf (fmf)");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").setIndex("looplevel", 5, 0);
    model.result("pg1").set("dataisaxisym", "off");
    model.result("pg1").feature().create("line1", "Line");
    model.result("pg1").feature("line1").label("\u7ebf");
    model.result("pg1").feature("line1").set("showsolutionparams", "on");
    model.result("pg1").feature("line1").set("expr", "fmf.Gtot");
    model.result("pg1").feature("line1").set("linetype", "tube");
    model.result("pg1").feature("line1").set("resolution", "norefine");
    model.result("pg1").feature("line1").set("smooth", "internal");
    model.result("pg1").feature("line1").set("showsolutionparams", "on");
    model.result("pg1").feature("line1").set("data", "parent");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u603b\u6570\u5bc6\u5ea6 (fmf)");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").setIndex("looplevel", 5, 0);
    model.result("pg2").set("dataisaxisym", "off");
    model.result("pg2").feature().create("line1", "Line");
    model.result("pg2").feature("line1").label("\u7ebf");
    model.result("pg2").feature("line1").set("showsolutionparams", "on");
    model.result("pg2").feature("line1").set("expr", "fmf.ntot");
    model.result("pg2").feature("line1").set("linetype", "tube");
    model.result("pg2").feature("line1").set("resolution", "norefine");
    model.result("pg2").feature("line1").set("smooth", "internal");
    model.result("pg2").feature("line1").set("showsolutionparams", "on");
    model.result("pg2").feature("line1").set("data", "parent");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u603b\u538b (fmf)");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").setIndex("looplevel", 5, 0);
    model.result("pg3").set("dataisaxisym", "off");
    model.result("pg3").feature().create("line1", "Line");
    model.result("pg3").feature("line1").label("\u7ebf");
    model.result("pg3").feature("line1").set("showsolutionparams", "on");
    model.result("pg3").feature("line1").set("expr", "fmf.ptot");
    model.result("pg3").feature("line1").set("linetype", "tube");
    model.result("pg3").feature("line1").set("resolution", "norefine");
    model.result("pg3").feature("line1").set("smooth", "internal");
    model.result("pg3").feature("line1").set("showsolutionparams", "on");
    model.result("pg3").feature("line1").set("data", "parent");
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u603b\u538b");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").setIndex("looplevelinput", "first", 0);
    model.result("pg4").create("lngr1", "LineGraph");
    model.result("pg4").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg4").feature("lngr1").set("linewidth", "preference");
    model.result("pg4").feature("lngr1").selection().set(4);
    model.result("pg4").feature("lngr1").set("expr", "fmf.ptot");
    model.result("pg4").feature("lngr1").set("descr", "\u603b\u538b");
    model.result("pg4").feature("lngr1").set("resolution", "norefine");
    model.result("pg4").feature("lngr1").set("xdata", "expr");
    model.result("pg4").feature("lngr1").set("xdataexpr", "z");
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").set("data", "dset2");
    model.result("pg5").create("glob1", "Global");
    model.result("pg5").feature("glob1").set("markerpos", "datapoints");
    model.result("pg5").feature("glob1").set("linewidth", "preference");
    model.result("pg5").feature("glob1").setIndex("expr", "intop2(2*pi*r*G)/intop1(2*pi*r*fmf.J_G)", 0);
    model.result("pg5").feature("glob1").setIndex("unit", "", 0);
    model.result("pg5").feature("glob1").setIndex("descr", "\u8ba1\u7b97\u51fa\u7684\u4f20\u8f93\u6982\u7387", 0);
    model.result("pg5").feature("glob1").set("xdata", "expr");
    model.result("pg5").feature("glob1").set("xdataexpr", "Lc/Rc");
    model.result("pg5").run();
    model.result("pg5").label("\u4f20\u8f93\u6982\u7387");
    model.result("pg5").set("xlabelactive", true);
    model.result("pg5").set("xlabel", "\u957f\u5ea6/\u534a\u5f84");
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("ylabel", "\u4f20\u8f93\u6982\u7387");
    model.result("pg5").create("glob2", "Global");
    model.result("pg5").feature("glob2").set("markerpos", "datapoints");
    model.result("pg5").feature("glob2").set("linewidth", "preference");
    model.result("pg5").feature("glob2").setIndex("expr", "int1(Lc/Rc)", 0);
    model.result("pg5").feature("glob2").setIndex("descr", "Cole \u5f97\u51fa\u7684\u7ed3\u679c", 0);
    model.result("pg5").feature("glob2").set("xdata", "expr");
    model.result("pg5").feature("glob2").set("xdataexpr", "Lc/Rc");
    model.result("pg5").feature("glob2").set("linestyle", "none");
    model.result("pg5").feature("glob2").set("linemarker", "square");
    model.result("pg5").run();
    model.result("pg5").run();

    model.title("\u6bdb\u7ec6\u7ba1\u4e2d\u7684\u5206\u5b50\u6d41");

    model
         .description("\u5206\u5b50\u5728\u5706\u7b52\u4e2d\u5411\u4e0b\u6d41\u52a8\u662f\u6700\u65e9\u5f97\u5230\u89e3\u6790\u5904\u7406\u7684\u95ee\u9898\u3002\u5728\u8fd9\u4e2a\u57fa\u51c6\u95ee\u9898\u4e2d\uff0c\u6211\u4eec\u8ba1\u7b97\u4e86\u957f\u5ea6\u53ef\u53d8\u7684\u6bdb\u7ec6\u7ba1\u4e2d\u5206\u5b50\u5411\u4e0b\u6d41\u52a8\u7684\u4f20\u8f93\u6982\u7387\uff0c\u5e76\u5c06\u5206\u6790\u7ed3\u679c\u4e0e\u89e3\u6790\u89e3\u8fdb\u884c\u6bd4\u8f83\u3002");

    model.label("vacuum_capillary.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
