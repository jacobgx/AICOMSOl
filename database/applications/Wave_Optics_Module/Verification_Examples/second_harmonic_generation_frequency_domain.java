/*
 * second_harmonic_generation_frequency_domain.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:43 by COMSOL 6.3.0.290. */
public class second_harmonic_generation_frequency_domain {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Wave_Optics_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("ewfd", "ElectromagneticWavesFrequencyDomain", "geom1");
    model.component("comp1").physics().create("ewfd2", "ElectromagneticWavesFrequencyDomain", "geom1");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").set("ftplistmethod", "manual");
    model.study("std1").feature("freq").set("solnum", "auto");
    model.study("std1").feature("freq").set("notsolnum", "auto");
    model.study("std1").feature("freq").set("outputmap", new String[]{});
    model.study("std1").feature("freq").set("ngenAUX", "1");
    model.study("std1").feature("freq").set("goalngenAUX", "1");
    model.study("std1").feature("freq").set("ngenAUX", "1");
    model.study("std1").feature("freq").set("goalngenAUX", "1");
    model.study("std1").feature("freq").setSolveFor("/physics/ewfd", true);
    model.study("std1").feature("freq").setSolveFor("/physics/ewfd2", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("lambda1", "1.064[um]", "\u57fa\u6ce2\u957f");
    model.param().set("f1", "c_const/lambda1", "\u57fa\u9891");
    model.param().set("f2", "2*f1", "\u4e8c\u6b21\u8c10\u6ce2\u9891\u7387");
    model.param().set("lambda2", "c_const/f2", "\u4e8c\u6b21\u8c10\u6ce2\u6ce2\u957f");
    model.param().set("sim_l", "lambda1*25", "\u4eff\u771f\u957f\u5ea6");
    model.param().set("sim_h", "lambda2/16", "\u4eff\u771f\u9ad8\u5ea6");
    model.param().set("d", "1e-18[C/V^2]", "\u975e\u7ebf\u6027\u7cfb\u6570");
    model.param().set("L", "sim_l - 3*lambda1", "\u975e\u7ebf\u6027\u533a\u57df\u957f\u5ea6");
    model.param().set("I1", "30[MW/m^2]", "\u5165\u5c04\u57fa\u672c\u5f3a\u5ea6");
    model.param().set("E1", "sqrt(2*I1/c_const/epsilon0_const)", "\u5165\u5c04\u57fa\u672c\u7535\u573a\u5f3a\u5ea6");
    model.param().set("offset", "1.5*lambda1", "\u975e\u7ebf\u6027\u533a\u57df\u8d77\u59cb\u4f4d\u7f6e");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"sim_l", "sim_h"});
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "offset", 0);
    model.component("comp1").geom("geom1").feature("r1").set("layerleft", true);
    model.component("comp1").geom("geom1").feature("r1").set("layerright", true);
    model.component("comp1").geom("geom1").feature("r1").set("layerbottom", false);

    model.component("comp1").view("view1").axis().set("viewscaletype", "automatic");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").set("n", new String[]{"1"});

    model.component("comp1").physics("ewfd").label("\u57fa\u6ce2");
    model.component("comp1").physics("ewfd").tag("ewfd1");
    model.component("comp1").physics("ewfd1").prop("components").set("components", "inplane");
    model.component("comp1").physics("ewfd1").create("pol1", "Polarization", 2);
    model.component("comp1").physics("ewfd1").feature("pol1").selection().set(2);
    model.component("comp1").physics("ewfd1").feature("pol1")
         .set("P", new String[]{"0", "2*d*ewfd2.Ey*conj(ewfd1.Ey)", "0"});
    model.component("comp1").physics("ewfd1").create("sctr1", "Scattering", 1);
    model.component("comp1").physics("ewfd1").feature("sctr1").selection().set(1);
    model.component("comp1").physics("ewfd1").feature("sctr1").set("IncidentField", "EField");
    model.component("comp1").physics("ewfd1").feature("sctr1").set("E0i", new String[]{"0", "E1", "0"});
    model.component("comp1").physics("ewfd1").create("sctr2", "Scattering", 1);
    model.component("comp1").physics("ewfd1").feature("sctr2").selection().set(10);
    model.component("comp1").physics("ewfd2").label("\u4e8c\u6b21\u8c10\u6ce2");
    model.component("comp1").physics("ewfd2").prop("components").set("components", "inplane");
    model.component("comp1").physics("ewfd2").prop("EquationForm").setIndex("form", "Frequency", 0);
    model.component("comp1").physics("ewfd2").prop("EquationForm").setIndex("freq_src", "userdef", 0);
    model.component("comp1").physics("ewfd2").prop("EquationForm").setIndex("freq", "f2", 0);
    model.component("comp1").physics("ewfd2").create("pol1", "Polarization", 2);
    model.component("comp1").physics("ewfd2").feature("pol1").selection().set(2);
    model.component("comp1").physics("ewfd2").feature("pol1")
         .set("P", new String[]{"0", "d*ewfd1.Ey*ewfd1.Ey", "0"});
    model.component("comp1").physics("ewfd2").create("sctr1", "Scattering", 1);
    model.component("comp1").physics("ewfd2").feature("sctr1").selection().set(1, 10);

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "sim_h");
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", "sim_h");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("gamma", "sqrt(8*d^2*Z0_const^3*(2*pi*ewfd1.freq)^2*I1)");
    model.component("comp1").variable("var1").descr("gamma", "\u8026\u5408\u7cfb\u6570");

    model.study("std1").feature("freq").set("plist", "f1");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("st1").set("splitcomplex", true);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u7535\u573a (ewfd1)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u7535\u573a (ewfd2)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "ewfd2.normE");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg1").run();
    model.result("pg1").label("\u57fa\u6ce2");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("expr", "ewfd1.Ey");
    model.result("pg1").run();
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").set("title", "\u57fa\u6ce2\u7684\u7535\u573a\uff0cy \u5206\u91cf (V/m)");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").label("\u4e8c\u6b21\u8c10\u6ce2");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("expr", "ewfd2.Ey");
    model.result("pg2").run();
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("title", "\u4e8c\u6b21\u8c10\u6ce2\u7684\u7535\u573a\uff0cy \u5206\u91cf (V/m)");
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u7535\u573a");
    model.result("pg3").create("lngr1", "LineGraph");
    model.result("pg3").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg3").feature("lngr1").set("linewidth", "preference");
    model.result("pg3").feature("lngr1").label("\u57fa\u6ce2");
    model.result("pg3").feature("lngr1").selection().set(2, 5, 8);
    model.result("pg3").feature("lngr1").set("expr", "ewfd1.Ey");
    model.result("pg3").feature("lngr1").set("xdata", "expr");
    model.result("pg3").feature("lngr1").set("xdataexpr", "x");
    model.result("pg3").feature("lngr1").set("linewidth", 2);
    model.result("pg3").feature("lngr1").set("legend", true);
    model.result("pg3").feature("lngr1").set("autoplotlabel", true);
    model.result("pg3").feature("lngr1").set("autosolution", false);
    model.result("pg3").feature().duplicate("lngr2", "lngr1");
    model.result("pg3").run();
    model.result("pg3").feature("lngr2").label("\u4e8c\u6b21\u8c10\u6ce2");
    model.result("pg3").feature("lngr2").set("expr", "ewfd2.Ey");
    model.result("pg3").run();
    model.result("pg3").set("titletype", "manual");
    model.result("pg3").set("title", "\u57fa\u6ce2\u548c\u4e8c\u6b21\u8c10\u6ce2\u7684\u7535\u573a");
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u5149\u5b50\u901a\u91cf\u5bc6\u5ea6");
    model.result("pg4").create("lngr1", "LineGraph");
    model.result("pg4").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg4").feature("lngr1").set("linewidth", "preference");
    model.result("pg4").feature("lngr1").label("\u57fa\u6ce2\u4eff\u771f");
    model.result("pg4").feature("lngr1").selection().set(2, 5, 8);
    model.result("pg4").feature("lngr1")
         .set("expr", "ewfd1.Ey*conj(ewfd1.Ey)/(2*Z0_const)/hbar_const/(2*pi*ewfd1.freq)");
    model.result("pg4").feature("lngr1").set("xdata", "expr");
    model.result("pg4").feature("lngr1").set("xdataexpr", "x");
    model.result("pg4").feature("lngr1").set("linestyle", "none");
    model.result("pg4").feature("lngr1").set("linewidth", 5);
    model.result("pg4").feature("lngr1").set("linemarker", "diamond");
    model.result("pg4").feature("lngr1").set("markerpos", "interp");
    model.result("pg4").feature("lngr1").set("markers", 20);
    model.result("pg4").feature("lngr1").set("legend", true);
    model.result("pg4").feature("lngr1").set("autoplotlabel", true);
    model.result("pg4").feature("lngr1").set("autosolution", false);
    model.result("pg4").feature().duplicate("lngr2", "lngr1");
    model.result("pg4").run();
    model.result("pg4").feature("lngr2").label("\u4e8c\u6b21\u8c10\u6ce2\u4eff\u771f");
    model.result("pg4").feature("lngr2")
         .set("expr", "ewfd2.Ey*conj(ewfd2.Ey)/(2*Z0_const)/hbar_const/(2*pi*ewfd2.freq)");
    model.result("pg4").feature().duplicate("lngr3", "lngr2");
    model.result("pg4").run();
    model.result("pg4").feature("lngr3").label("\u57fa\u6ce2\u7684\u6162\u53d8\u5305\u7edc\u8fd1\u4f3c (SVEA)");
    model.result("pg4").feature("lngr3").selection().set(5);
    model.result("pg4").feature("lngr3")
         .set("expr", "(sech(gamma*(x - offset)/2))^2*I1/hbar_const/(2*pi*ewfd1.freq)");
    model.result("pg4").feature("lngr3").set("linestyle", "solid");
    model.result("pg4").feature("lngr3").set("linewidth", 2);
    model.result("pg4").feature("lngr3").set("linemarker", "none");
    model.result("pg4").feature("lngr3").set("legendmethod", "manual");
    model.result("pg4").feature("lngr3").setIndex("legends", "\u57fa\u6ce2\u7684 SVEA", 0);
    model.result("pg4").feature().duplicate("lngr4", "lngr3");
    model.result("pg4").run();
    model.result("pg4").feature("lngr4")
         .label("\u4e8c\u6b21\u8c10\u6ce2\u7684\u6162\u53d8\u5305\u7edc\u8fd1\u4f3c (SVEA)");
    model.result("pg4").feature("lngr4")
         .set("expr", "(tanh(gamma*(x - offset)/2))^2*I1/hbar_const/(2*pi*ewfd2.freq)");
    model.result("pg4").feature("lngr4").setIndex("legends", "\u4e8c\u6b21\u8c10\u6ce2\u7684 SVEA", 0);
    model.result("pg4").run();
    model.result("pg4").set("titletype", "manual");
    model.result("pg4")
         .set("title", "\u57fa\u6ce2\u548c\u4e8c\u6b21\u8c10\u6ce2\u7684\u5149\u5b50\u901a\u91cf\u5bc6\u5ea6");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4")
         .set("ylabel", "\u5149\u5b50\u901a\u91cf\u5bc6\u5ea6\uff08\u6bcf\u5e73\u65b9\u7c73\u6bcf\u79d2\u7684\u5149\u5b50\u6570\uff09");

    model.title("\u9891\u57df\u4e2d\u7684\u4e8c\u6b21\u8c10\u6ce2\u4ea7\u751f");

    model
         .description("\u8fd9\u662f\u4e00\u4e2a\u539f\u7406\u8bba\u8bc1\u793a\u4f8b\uff0c\u4f7f\u7528\u4e24\u4e2a\u201c\u7535\u78c1\u6ce2\uff0c\u9891\u57df\u201d\u63a5\u53e3\u63cf\u8ff0\u4e8c\u6b21\u8c10\u6ce2\u4ea7\u751f (SHG) \u8fc7\u7a0b\u3002\u4e00\u4e2a\u7528\u4e8e\u57fa\u6ce2\uff0c\u4e00\u4e2a\u7528\u4e8e\u4e8c\u6b21\u8c10\u6ce2\u3002\n\n\u4e3a\u65b9\u4fbf\u8d77\u89c1\uff0c\u6bcf\u4e2a\u9891\u7387\u7684\u6298\u5c04\u7387\u90fd\u5b8c\u5168\u5339\u914d (n = 1)\u3002\u6ce2\u4e4b\u95f4\u7684\u8026\u5408\u901a\u8fc7\u6bcf\u4e2a\u63a5\u53e3\u7684\u57df\u201c\u504f\u632f\u201d\u7279\u5f81\u5b9e\u73b0\u3002\n\n\u5176\u4e2d\u5c06\u7ed3\u679c\u4e0e\u901a\u8fc7\u201c\u6162\u53d8\u5305\u7edc\u8fd1\u4f3c\u201d(SVEA) \u5f97\u5230\u7684\u89e3\u6790\u89e3\u8fdb\u884c\u6bd4\u8f83\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("second_harmonic_generation_frequency_domain.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
