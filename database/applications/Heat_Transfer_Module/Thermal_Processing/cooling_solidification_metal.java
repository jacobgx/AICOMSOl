/*
 * cooling_solidification_metal.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:30 by COMSOL 6.3.0.290. */
public class cooling_solidification_metal {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Heat_Transfer_Module\\Thermal_Processing");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("ht", "HeatTransferInFluids", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/ht", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("T_m", "1356[K]", "\u7194\u5316\u6e29\u5ea6");
    model.param().set("dT", "50[K]", "\u6e29\u5ea6\u8fc7\u6e21\u533a\u534a\u5bbd");
    model.param().set("dH", "205[kJ/kg]", "\u51dd\u56fa\u6f5c\u70ed");
    model.param().set("Cp_S", "380[J/(kg*K)]", "\u6052\u538b\u70ed\u5bb9\uff0c\u56fa\u76f8");
    model.param().set("Cp_L", "530[J/(kg*K)]", "\u6052\u538b\u70ed\u5bb9\uff0c\u6db2\u76f8");
    model.param().set("T0", "300[K]", "\u73af\u5883\u6e29\u5ea6");
    model.param().set("T_in", "1473[K]", "\u7194\u4f53\u5165\u53e3\u6e29\u5ea6");
    model.param().set("v_cast", "2.5[mm/s]", "\u94f8\u9020\u901f\u5ea6");
    model.param().set("h_mold", "800[W/(m^2*K)]", "\u4f20\u70ed\u7cfb\u6570\uff0c\u6a21\u5177");
    model.param()
         .set("h_spray", "200[W/(m^2*K)]", "\u4f20\u70ed\u7cfb\u6570\uff0c\u55b7\u96fe\u51b7\u5374\u533a\u57df");
    model.param().set("eps_s", "0.8", "\u8868\u9762\u53d1\u5c04\u7387");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new double[]{0.1, 0.6});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new double[]{0.1, 0.2});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new double[]{0, 0.6});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u56fa\u6001\u91d1\u5c5e\u5408\u91d1");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"300[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"8500[kg/m^3]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", new String[]{"Cp_S"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u6db2\u6001\u91d1\u5c5e\u5408\u91d1");
    model.component("comp1").material("mat2").selection().set(1, 2);
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"150[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", new String[]{"8500[kg/m^3]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", new String[]{"Cp_L"});

    model.component("comp1").physics("ht").feature("init1").set("Tinit", "T_in");
    model.component("comp1").physics("ht").feature("fluid1").set("u", new String[]{"0", "0", "-v_cast"});
    model.component("comp1").physics("ht").feature("fluid1").create("phc1", "PhaseChangeMaterial", 2);
    model.component("comp1").physics("ht").feature("fluid1").feature("phc1").set("T_pc12", "T_m");
    model.component("comp1").physics("ht").feature("fluid1").feature("phc1").set("dT_pc12", "dT");
    model.component("comp1").physics("ht").feature("fluid1").feature("phc1").set("L_pc12", "dH");
    model.component("comp1").physics("ht").feature("fluid1").feature("phc1").set("MaterialPhase1", "mat1");
    model.component("comp1").physics("ht").feature("fluid1").feature("phc1").set("MaterialPhase2", "mat2");
    model.component("comp1").physics("ht").create("ifl1", "Inflow", 1);
    model.component("comp1").physics("ht").feature("ifl1").selection().set(5);
    model.component("comp1").physics("ht").feature("ifl1").set("Tustr", "T_in");
    model.component("comp1").physics("ht").create("hf1", "HeatFluxBoundary", 1);
    model.component("comp1").physics("ht").feature("hf1").selection().set(7);
    model.component("comp1").physics("ht").feature("hf1").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf1").set("h", "h_mold");
    model.component("comp1").physics("ht").feature("hf1").set("Text", "T0");
    model.component("comp1").physics("ht").create("hf2", "HeatFluxBoundary", 1);
    model.component("comp1").physics("ht").feature("hf2").selection().set(6);
    model.component("comp1").physics("ht").feature("hf2").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf2").set("h", "h_spray");
    model.component("comp1").physics("ht").feature("hf2").set("Text", "T0");
    model.component("comp1").physics("ht").create("sar1", "SurfaceToAmbientRadiation", 1);
    model.component("comp1").physics("ht").feature("sar1").selection().set(6);
    model.component("comp1").physics("ht").feature("sar1").set("epsilon_rad_mat", "userdef");
    model.component("comp1").physics("ht").feature("sar1").set("epsilon_rad", "eps_s");
    model.component("comp1").physics("ht").feature("sar1").set("Tamb", "T0");
    model.component("comp1").physics("ht").create("ofl1", "ConvectiveOutflow", 1);
    model.component("comp1").physics("ht").feature("ofl1").selection().set(2);

    model.component("comp1").mesh("mesh1").autoMeshSize(1);

    model.study("std1").setGenPlots(false);
    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "T_m", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "K", 0);
    model.study("std1").feature("stat").setIndex("pname", "T_m", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "K", 0);
    model.study("std1").feature("stat").setIndex("pname", "dT", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "300 200 150 100 75", 0);
    model.study("std1").feature("stat").set("errestandadap", "adaption");
    model.study("std1").feature("stat").set("meshadaptmethod", "rebuild");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").run();
    model.result("pg1").label("\u56fa\u76f8\u548c\u6db2\u76f8\uff08\u81ea\u9002\u5e94\u7f51\u683c\uff09");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", "ht.theta1");
    model.result("pg1").feature("surf1").set("descr", "\u76f8\u6307\u793a\u5668\uff0c\u76f8 1");
    model.result("pg1").run();

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std2").setGenPlots(false);
    model.study("std2").feature("stat").set("useinitsol", true);
    model.study("std2").feature("stat").set("initmethod", "sol");
    model.study("std2").feature("stat").set("initstudy", "std1");
    model.study("std2").feature("stat").set("initsol", "sol2");
    model.study("std2").feature("stat").set("initsoluse", "sol5");
    model.study("std2").feature("stat").set("solnum", 5);
    model.study("std2").feature("stat").set("useparam", true);
    model.study("std2").feature("stat").setIndex("pname", "T_m", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "K", 0);
    model.study("std2").feature("stat").setIndex("pname", "T_m", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "K", 0);
    model.study("std2").feature("stat").setIndex("pname", "dT", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "50 25", 0);
    model.study("std2").feature("stat").set("usestol", true);
    model.study("std2").feature("stat").set("stol", "1e-5");
    model.study("std2").createAutoSequences("all");

    model.sol("sol6").runAll();

    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").run();
    model.result("pg2").label("\u56fa\u76f8\u548c\u6db2\u76f8");
    model.result("pg2").set("data", "dset3");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "ht.theta1");
    model.result("pg2").feature("surf1").set("descr", "\u76f8\u6307\u793a\u5668\uff0c\u76f8 1");
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u5bf9\u79f0\u8f74\u5904\u7684\u76f8\u6307\u793a\u5668");
    model.result("pg3").create("lngr1", "LineGraph");
    model.result("pg3").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg3").feature("lngr1").set("linewidth", "preference");
    model.result("pg3").feature("lngr1").selection().set(1, 3);
    model.result("pg3").feature("lngr1").set("expr", "ht.theta1");
    model.result("pg3").feature("lngr1").set("descr", "\u76f8\u6307\u793a\u5668\uff0c\u76f8 1");
    model.result("pg3").feature("lngr1").set("xdata", "expr");
    model.result("pg3").feature("lngr1").set("xdataexpr", "z");
    model.result("pg3").feature("lngr1").set("legend", true);
    model.result("pg3").feature().duplicate("lngr2", "lngr1");
    model.result("pg3").run();
    model.result("pg3").feature("lngr2").set("data", "dset3");
    model.result("pg3").feature("lngr2").set("titletype", "none");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result().duplicate("pg4", "pg3");
    model.result("pg4").run();
    model.result("pg4").label("\u901a\u8fc7\u534a\u5f84\u7684\u76f8\u6307\u793a\u5668");
    model.result("pg4").run();
    model.result("pg4").feature("lngr1").selection().set(4);
    model.result("pg4").feature("lngr1").set("xdataexpr", "r");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").feature("lngr2").selection().set(4);
    model.result("pg4").feature("lngr2").set("xdataexpr", "r");
    model.result("pg4").run();
    model.result("pg4").set("legendpos", "upperleft");
    model.result("pg4").run();

    model.title("\u91d1\u5c5e\u51b7\u5374\u548c\u51dd\u56fa");

    model
         .description("\u8fd9\u662f\u4e00\u4e2a\u8fde\u94f8\u5de5\u827a\u6a21\u578b\uff0c\u5176\u4e2d\u7684\u6db2\u6001\u91d1\u5c5e\u5728\u901a\u8fc7\u51b7\u5374\u6a21\u5177\u65f6\u53d1\u751f\u964d\u6e29\u51dd\u56fa\u3002\u901a\u8fc7\u8868\u89c2\u70ed\u5bb9\u6cd5\u5bf9\u5176\u4e2d\u7684\u76f8\u53d8\u5efa\u6a21\uff0c\u4f7f\u7528\u7f51\u683c\u81ea\u9002\u5e94\u548c\u8fde\u7eed\u6cd5\u6765\u6c42\u89e3\u9ad8\u5ea6\u975e\u7ebf\u6027\u7684\u7a33\u6001\u95ee\u9898\u3002\u76ee\u6807\u662f\u627e\u51fa\u51dd\u56fa\u524d\u6cbf\u7684\u5f62\u72b6\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();

    model.label("cooling_solidification_metal.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
