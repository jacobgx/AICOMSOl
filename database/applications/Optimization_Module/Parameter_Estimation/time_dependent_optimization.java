/*
 * time_dependent_optimization.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:42 by COMSOL 6.3.0.290. */
public class time_dependent_optimization {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Optimization_Module\\Parameter_Estimation");

    model.component().create("comp1", true);

    model.component("comp1").physics().create("ge", "GlobalEquations");
    model.component("comp1").physics("ge").prop("EquationForm").set("form", "Automatic");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/ge", true);

    model.param().set("a", "0.25");
    model.param().descr("a", "\u5e38\u5fae\u5206\u65b9\u7a0b\u5e38\u6570 1");
    model.param().set("b", "0.05");
    model.param().descr("b", "\u5e38\u5fae\u5206\u65b9\u7a0b\u5e38\u6570 2");
    model.param().set("c", "0.015");
    model.param().descr("c", "\u5e38\u5fae\u5206\u65b9\u7a0b\u5e38\u6570 3");
    model.param().set("u0", "0.25");
    model.param().descr("u0", "\u521d\u59cb\u503c");
    model.param().set("f", "1[Hz]");
    model.param().descr("f", "\u9891\u7387");
    model.param().set("w", "2*pi*f");
    model.param().descr("w", "\u89d2\u9891\u7387");

    model.component("comp1").physics("ge").feature("ge1").setIndex("name", "u", 0, 0);
    model.component("comp1").physics("ge").feature("ge1")
         .setIndex("equation", "((1/f)*ut-a*sin(w*t)^2+b*u+c*u^2)", 0, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("initialValueU", "u0", 0, 0);

    model.study("std1").feature("time").set("usertol", true);
    model.study("std1").feature("time").set("rtol", "1e-5");
    model.study("std1").feature("time").set("tlist", "range(0,0.01,100)");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "manual");
    model.sol("sol1").feature("t1").set("atolglobal", "0.0001");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").set("data", "dset1");
    model.result().numerical("gev1").set("expr", new String[]{"u"});
    model.result().numerical("gev1").set("descr", new String[]{"\u72b6\u6001\u53d8\u91cf u"});
    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").create("glob1", "Global");
    model.result("pg1").feature("glob1").set("expr", new String[]{"u"});
    model.result("pg1").feature("glob1").set("descr", new String[]{"\u72b6\u6001\u53d8\u91cf u"});
    model.result("pg1").run();
    model.result("pg1").set("legendpos", "lowerright");
    model.result("pg1").run();
    model.result().duplicate("pg2", "pg1");
    model.result("pg2").run();
    model.result("pg2").set("axislimits", true);
    model.result("pg2").set("xmin", 90);
    model.result("pg2").set("ymin", 1.6);
    model.result("pg2").set("ymax", 1.7);
    model.result("pg2").run();

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/ge", true);
    model.study("std2").feature("time").set("tlist", "range(0,0.002,1)");
    model.study("std2").feature("time").set("usertol", true);
    model.study("std2").feature("time").set("rtol", "1e-5");
    model.study("std2").create("opt", "Optimization");
    model.study("std2").feature("opt").set("optsolver", "ipopt");
    model.study("std2").feature("opt").setIndex("optobj", "(comp1.u-u0)^2", 0);
    model.study("std2").feature("opt").setIndex("descr", "\u5e73\u65b9\u8bef\u5dee", 0);
    model.study("std2").feature("opt").setIndex("pname", "a", 0);
    model.study("std2").feature("opt").setIndex("initval", 0.25, 0);
    model.study("std2").feature("opt").setIndex("scale", 1, 0);
    model.study("std2").feature("opt").setIndex("lbound", "", 0);
    model.study("std2").feature("opt").setIndex("ubound", "", 0);
    model.study("std2").feature("opt").setIndex("pname", "a", 0);
    model.study("std2").feature("opt").setIndex("initval", 0.25, 0);
    model.study("std2").feature("opt").setIndex("scale", 1, 0);
    model.study("std2").feature("opt").setIndex("lbound", "", 0);
    model.study("std2").feature("opt").setIndex("ubound", "", 0);
    model.study("std2").feature("opt").setIndex("pname", "u0", 0);
    model.study("std2").feature("opt").setIndex("lbound", 0, 0);
    model.study("std2").feature("opt").setIndex("ubound", 5, 0);
    model.study("std2").showAutoSequences("all");

    model.sol("sol2").feature("o1").feature("t1").set("atolglobalvaluemethod", "manual");
    model.sol("sol2").feature("o1").feature("t1").set("atolglobal", "1e-5");

    model.study("std2").createAutoSequences("all");
    model.study("std2").feature("opt").set("continuecontrolparams", new String[]{});
    model.study("std2").feature("opt").set("continuecontrolvals", new double[]{});
    model.study("std2").feature("opt").set("continuelagrangevals", new double[]{});
    model.study("std2").feature("opt").set("continuelagrangeparams", new String[]{});

    model.sol("sol2").runAll();

    model.result().numerical().create("gev2", "EvalGlobal");
    model.result().numerical("gev2").set("data", "dset2");
    model.result().numerical("gev2").set("expr", new String[]{"u"});
    model.result().numerical("gev2").set("descr", new String[]{"\u72b6\u6001\u53d8\u91cf u"});
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("expr", new String[]{"u"});
    model.result("pg3").feature("glob1").set("descr", new String[]{"\u72b6\u6001\u53d8\u91cf u"});
    model.result("pg3").run();

    model.study("std2").feature("opt").set("probewindow", "");

    model.title("\u77ac\u6001\u4f18\u5316");

    model
         .description("\u672c\u6559\u7a0b\u6f14\u793a\u5982\u4f55\u4f7f\u7528\u77ac\u6001\u4f18\u5316\u6c42\u89e3\u5668\u8ba1\u7b97\u975e\u7ebf\u6027\u95ee\u9898\u7684\u5468\u671f\u6027\u7a33\u6001\u89e3\u3002\u6a21\u578b\u91c7\u7528\u77ac\u6001\u4f18\u5316\u6c42\u89e3\u5668\u6c42\u89e3\u7684\u901f\u5ea6\u4f1a\u5feb\u5f88\u591a\uff0c\u8fd9\u662f\u56e0\u4e3a\u65e0\u987b\u7ecf\u8fc7\u591a\u6b21\u6c42\u89e3\u3002");

    model.label("time_dependent_optimization.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
