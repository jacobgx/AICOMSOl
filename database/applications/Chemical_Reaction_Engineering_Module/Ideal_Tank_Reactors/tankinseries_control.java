/*
 * tankinseries_control.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:18 by COMSOL 6.3.0.290. */
public class tankinseries_control {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Chemical_Reaction_Engineering_Module\\Ideal_Tank_Reactors");

    model.component().create("comp1", true);

    model.component("comp1").physics().create("re", "ReactionEng");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/re", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("Vr_tank", "1[m^3]", "\u53cd\u5e94\u91dc\u4f53\u79ef");
    model.param().set("kf_reaction", "0.5[1/min]", "\u53cd\u5e94\u901f\u7387\u5e38\u6570");
    model.param().set("tau", "Vr_tank/v_tanks", "\u53cd\u5e94\u91dc\u4e2d\u7684\u505c\u7559\u65f6\u95f4");
    model.param()
         .set("v_tanks", "8[l/s]", "\u53cd\u5e94\u91dc\u5165\u53e3\u548c\u51fa\u53e3\u4e2d\u7684\u4f53\u79ef\u6d41\u91cf");
    model.param().set("Mn_A", "0.025[kg/mol]", "\u6469\u5c14\u8d28\u91cf\uff0cA");
    model.param().set("Mn_B", "0.025[kg/mol]", "\u6469\u5c14\u8d28\u91cf\uff0cB");
    model.param().set("Mn_solv", "0.018[kg/mol]", "\u6469\u5c14\u8d28\u91cf\uff0c\u6eb6\u5242");
    model.param().set("rho_spec", "1000[kg/m^3]", "\u5bc6\u5ea6\uff0c\u53cd\u5e94\u7269\u8d28");
    model.param().set("rho_solv", "1000[kg/m^3]", "\u5bc6\u5ea6\uff0c\u6eb6\u5242");
    model.param()
         .set("cinit_A_tank1", "400[mol/m^3]", "\u53cd\u5e94\u91dc 1 \u4e2d\u7684\u521d\u59cb\u6d53\u5ea6\uff0cA");
    model.param()
         .set("cinit_A_tank2", "200[mol/m^3]", "\u53cd\u5e94\u91dc 2 \u4e2d\u7684\u521d\u59cb\u6d53\u5ea6\uff0cA");
    model.param()
         .set("cinit_A_tank3", "100[mol/m^3]", "\u53cd\u5e94\u91dc 3 \u4e2d\u7684\u521d\u59cb\u6d53\u5ea6\uff0cA");
    model.param().set("cinlet_A", "1800[mol/m^3]", "\u5165\u53e3\u6d53\u5ea6\uff0cA");
    model.param().set("c_solv", "rho_solv/Mn_solv", "\u6d53\u5ea6\uff0c\u6eb6\u5242");
    model.param().set("Kc", "30", "\u63a7\u5236\u5668\u589e\u76ca");
    model.param().set("tau1", "5[min]", "\u590d\u4f4d\u65f6\u95f4");
    model.param().set("cset_A", "100[mol/m^3]", "\u8bbe\u7f6e\u7684\u6c34\u5e73\u6d53\u5ea6\uff0cA");
    model.param().set("cdisturb_A", "200[mol/m^3]", "\u6270\u52a8\u6d53\u5ea6\uff0cA");

    model.component("comp1").physics("re").tag("tank1");
    model.component("comp1").physics("tank1").prop("reactor").set("reactor", "cstrvol");
    model.component("comp1").physics("tank1").prop("reactor").set("Vr", "Vr_tank");
    model.component("comp1").physics("tank1").prop("mixture").set("mixture", "liquid");
    model.component("comp1").physics("tank1").create("rch1", "ReactionChem", -1);
    model.component("comp1").physics("tank1").feature("rch1").set("formula", "A=>B");
    model.component("comp1").physics("tank1").feature("rch1").set("kf", "kf_reaction");
    model.component("comp1").physics("tank1").feature("A").set("M", "Mn_A");
    model.component("comp1").physics("tank1").feature("A").set("rho", "rho_spec");
    model.component("comp1").physics("tank1").feature("B").set("M", "Mn_B");
    model.component("comp1").physics("tank1").feature("B").set("rho", "rho_spec");
    model.component("comp1").physics("tank1").create("spec1", "SpeciesChem", -1);
    model.component("comp1").physics("tank1").feature("spec1").set("specName", "H2O");
    model.component("comp1").physics("tank1").feature("H2O").set("sType", "solvent");
    model.component("comp1").physics("tank1").feature("H2O").set("M", "Mn_solv");
    model.component("comp1").physics("tank1").feature("H2O").set("rho", "rho_solv");
    model.component("comp1").physics("tank1").feature("inits1").setIndex("initialValue", "cinit_A_tank1", 0, 0);
    model.component("comp1").physics("tank1").feature("inits1").setIndex("initialValue", "c_solv", 2, 0);
    model.component("comp1").physics("tank1").create("feed1", "FeedStream", -1);
    model.component("comp1").physics("tank1").feature("feed1").set("vf", "v_tanks");
    model.component("comp1").physics("tank1").feature("feed1")
         .setIndex("FeedSpeciesConcentration", "cinlet_A", 0, 0);
    model.component("comp1").physics("tank1").feature("feed1").setIndex("FeedSpeciesConcentration", "c_solv", 2, 0);
    model.component("comp1").physics().copy("tank2", "tank1");
    model.component("comp1").physics("tank2").feature("inits1").setIndex("initialValue", "cinit_A_tank2", 0, 0);
    model.component("comp1").physics("tank2").feature("feed1")
         .setIndex("FeedSpeciesConcentration", "tank1.c_A", 0, 0);
    model.component("comp1").physics("tank2").feature("feed1")
         .setIndex("FeedSpeciesConcentration", "tank1.c_B", 1, 0);
    model.component("comp1").physics().copy("tank3", "tank2");
    model.component("comp1").physics("tank3").feature("inits1").setIndex("initialValue", "cinit_A_tank3", 0, 0);
    model.component("comp1").physics("tank3").feature("feed1")
         .setIndex("FeedSpeciesConcentration", "tank2.c_A", 0, 0);
    model.component("comp1").physics("tank3").feature("feed1")
         .setIndex("FeedSpeciesConcentration", "tank2.c_B", 1, 0);

    model.study("std1").feature("time").set("tlist", "range(0,1,600)");
    model.study("std1").setGenPlots(false);
    model.study("std1").setGenConv(false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();
    model.sol("sol1").copySolution("sol2");
    model.sol("sol2").label("\u5f00\u73af");

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").run();
    model.result("pg1").label("\u5f00\u73af");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").set("titletype", "none");
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "\u6d53\u5ea6 A (mol/m<sup>3</sup>)");
    model.result("pg1").set("legendlayout", "outside");
    model.result("pg1").create("glob1", "Global");
    model.result("pg1").feature("glob1").set("markerpos", "datapoints");
    model.result("pg1").feature("glob1").set("linewidth", "preference");
    model.result("pg1").feature("glob1").set("expr", new String[]{"tank1.c_A"});
    model.result("pg1").feature("glob1").set("descr", new String[]{"\u6d53\u5ea6"});
    model.result("pg1").feature("glob1").set("unit", new String[]{"mol/m^3"});
    model.result("pg1").feature("glob1").set("expr", new String[]{"tank1.c_A", "tank2.c_A"});
    model.result("pg1").feature("glob1").set("descr", new String[]{"\u6d53\u5ea6", "\u6d53\u5ea6"});
    model.result("pg1").feature("glob1").set("expr", new String[]{"tank1.c_A", "tank2.c_A", "tank3.c_A"});
    model.result("pg1").feature("glob1").set("descr", new String[]{"\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6"});
    model.result("pg1").feature("glob1").set("xdata", "expr");
    model.result("pg1").feature("glob1").set("xdataexpr", "t");
    model.result("pg1").feature("glob1").set("xdataunit", "min");
    model.result("pg1").feature("glob1").set("linewidth", 2);
    model.result("pg1").feature("glob1").set("legendmethod", "manual");
    model.result("pg1").feature("glob1").setIndex("legends", "\u91dc 1", 0);
    model.result("pg1").feature("glob1").setIndex("legends", "\u91dc 2", 1);
    model.result("pg1").feature("glob1").setIndex("legends", "\u91dc 3", 2);
    model.result("pg1").run();

    model.component("comp1").physics().create("ge", "GlobalEquations");

    model.study("std1").feature("time").setSolveFor("/physics/ge", true);

    model.component("comp1").physics("ge").prop("EquationForm").set("form", "Automatic");
    model.component("comp1").physics("ge").feature("ge1").setIndex("name", "E_int", 0, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("equation", "E_intt-E", 0, 0);
    model.component("comp1").physics("ge").feature("ge1").set("CustomDependentVariableUnit", "1");
    model.component("comp1").physics("ge").feature("ge1").set("DependentVariableQuantity", "none");
    model.component("comp1").physics("ge").feature("ge1").setIndex("CustomDependentVariableUnit", "mol/m^3*s", 0, 0);
    model.component("comp1").physics("ge").feature("ge1").set("SourceTermQuantity", "concentration");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("E", "cset_A-tank3.c_A");
    model.component("comp1").variable("var1").descr("E", "\u6d4b\u91cf\u8bef\u5dee");
    model.component("comp1").variable("var1").set("cM_A", "800+Kc*(E+E_int/tau1)");
    model.component("comp1").variable("var1").descr("cM_A", "\u64cd\u7eb5\u6d53\u5ea6");
    model.component("comp1").variable("var1").set("cinlet_A", "max((cM_A+cdisturb_A),0)");
    model.component("comp1").variable("var1").descr("cinlet_A", "\u5165\u53e3\u6d53\u5ea6");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();
    model.sol("sol1").label("\u95ed\u73af");

    model.result("pg1").run();
    model.result().duplicate("pg2", "pg1");
    model.result("pg2").run();
    model.result("pg2").label("\u95ed\u73af");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").run();
    model.result("pg2").feature("glob1")
         .set("expr", new String[]{"tank1.c_A", "tank2.c_A", "tank3.c_A", "cinlet_A"});
    model.result("pg2").feature("glob1")
         .set("descr", new String[]{"\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", "\u5165\u53e3\u6d53\u5ea6"});
    model.result("pg2").feature("glob1").setIndex("legends", "\u91dc 1 \u7684\u5165\u53e3\u5904", 3);
    model.result("pg2").run();

    model.title("\u5e26\u53cd\u9988\u63a7\u5236\u7684\u7ea7\u8054\u53cd\u5e94\u91dc");

    model
         .description("\u672c\u4f8b\u63cf\u8ff0\u5982\u4f55\u4f7f\u7528\u201c\u53cd\u5e94\u5de5\u7a0b\u201d\u63a5\u53e3\u8bbe\u7f6e\u548c\u6c42\u89e3\u7ea7\u8054\u53cd\u5e94\u91dc\u3002\u901a\u8fc7\u53cd\u9988\u63a7\u5236\u73af\u8c03\u6574\u7b2c\u4e00\u4e2a\u91dc\u7684\u5165\u53e3\u6d53\u5ea6\uff0c\u4f7f\u6700\u540e\u4e00\u4e2a\u53cd\u5e94\u5668\u7684\u51fa\u53e3\u6d53\u5ea6\u4fdd\u6301\u5728\u67d0\u4e00\u8bbe\u7f6e\u7684\u6c34\u5e73\u3002");

    model.label("tankinseries_control.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
