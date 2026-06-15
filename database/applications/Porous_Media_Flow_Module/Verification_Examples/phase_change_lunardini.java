/*
 * phase_change_lunardini.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:14 by COMSOL 6.3.0.290. */
public class phase_change_lunardini {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Porous_Media_Flow_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 1);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("ht", "PorousMediaHeatTransfer", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/ht", true);

    model.component("comp1").geom("geom1").create("i1", "Interval");
    model.component("comp1").geom("geom1").feature("i1").setIndex("coord", 10, 1);
    model.component("comp1").geom("geom1").runPre("fin");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("por", "0.336", "\u5b54\u9699\u7387");
    model.param().set("Sw_res", "0.391", "\u6b8b\u4f59\u6c34\u9971\u548c\u5ea6");
    model.param().set("rho_solid", "2530.1[kg/m^3]", "\u571f\u58e4\u5bc6\u5ea6");
    model.param().set("rho_water", "1000[kg/m^3]", "\u6c34\u7684\u5bc6\u5ea6");
    model.param().set("rho_ice", "920[kg/m^3]", "\u51b0\u7684\u5bc6\u5ea6");
    model.param().set("k_water", "0.598[W/m/K]", "\u6c34\u7684\u5bfc\u70ed\u7cfb\u6570");
    model.param().set("Cv", "690030[J/m^3/K]", "\u4f53\u79ef\u70ed\u5bb9");
    model.param().set("L", "334560[J/kg]", "\u51b7\u51bb\u6f5c\u70ed");
    model.param()
         .set("k_solid", "(2.417196[W/m/K]-por*k_water)/(1-por)", "\u571f\u58e4\u7684\u5bfc\u70ed\u7cfb\u6570");
    model.param()
         .set("k_ice", "((3.462696[W/m/K]-(1-por)*k_solid)/por-Sw_res*k_water)/(1-Sw_res)", "\u51b0\u7684\u5bfc\u70ed\u7cfb\u6570");
    model.param().set("T_init", "4[degC]", "\u521d\u59cb\u6e29\u5ea6");
    model.param().set("T_in", "-6[degC]", "\u65bd\u52a0\u7684\u6e29\u5ea6");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("ht").feature("porous1").feature("fluid1")
         .create("phc1", "PhaseChangeMaterial", 1);
    model.component("comp1").physics("ht").feature("porous1").feature("fluid1").feature("phc1")
         .set("PhaseTransitionFunction12", "userdef");
    model.component("comp1").physics("ht").feature("porous1").feature("fluid1").feature("phc1").set("L_pc12", "L");
    model.component("comp1").physics("ht").feature("porous1").feature("fluid1").feature("phc1")
         .set("alpha12", "f_phtr(T)");
    model.component("comp1").physics("ht").feature("porous1").feature("fluid1").feature("phc1")
         .set("k_phase1_mat", "userdef");
    model.component("comp1").physics("ht").feature("porous1").feature("fluid1").feature("phc1")
         .set("k_phase1", new String[]{"k_ice", "0", "0", "0", "k_ice", "0", "0", "0", "k_ice"});
    model.component("comp1").physics("ht").feature("porous1").feature("fluid1").feature("phc1")
         .set("rho_phase1_mat", "userdef");
    model.component("comp1").physics("ht").feature("porous1").feature("fluid1").feature("phc1")
         .set("rho_phase1", "rho_ice");
    model.component("comp1").physics("ht").feature("porous1").feature("fluid1").feature("phc1")
         .set("Cp_phase1_mat", "userdef");
    model.component("comp1").physics("ht").feature("porous1").feature("fluid1").feature("phc1")
         .set("Cp_phase1", "Cv/rho_ice");
    model.component("comp1").physics("ht").feature("porous1").feature("fluid1").feature("phc1")
         .set("k_phase2_mat", "userdef");
    model.component("comp1").physics("ht").feature("porous1").feature("fluid1").feature("phc1")
         .set("k_phase2", new String[]{"k_water", "0", "0", "0", "k_water", "0", "0", "0", "k_water"});
    model.component("comp1").physics("ht").feature("porous1").feature("fluid1").feature("phc1")
         .set("rho_phase2_mat", "userdef");
    model.component("comp1").physics("ht").feature("porous1").feature("fluid1").feature("phc1")
         .set("rho_phase2", "rho_water");
    model.component("comp1").physics("ht").feature("porous1").feature("fluid1").feature("phc1")
         .set("Cp_phase2_mat", "userdef");
    model.component("comp1").physics("ht").feature("porous1").feature("fluid1").feature("phc1")
         .set("Cp_phase2", "Cv/rho_water");

    model.component("comp1").func().create("int1", "Interpolation");
    model.component("comp1").func("int1").set("funcname", "f_phtr");
    model.component("comp1").func("int1").setIndex("table", -4, 0, 0);
    model.component("comp1").func("int1").setIndex("table", "Sw_res", 0, 1);
    model.component("comp1").func("int1").setIndex("table", -1, 1, 0);
    model.component("comp1").func("int1").setIndex("table", "Sw_res", 1, 1);
    model.component("comp1").func("int1").setIndex("table", 0, 2, 0);
    model.component("comp1").func("int1").setIndex("table", 1, 2, 1);
    model.component("comp1").func("int1").setIndex("table", 6, 3, 0);
    model.component("comp1").func("int1").setIndex("table", 1, 3, 1);
    model.component("comp1").func("int1").setIndex("fununit", 1, 0);
    model.component("comp1").func("int1").setIndex("argunit", "degC", 0);

    model.component("comp1").physics("ht").feature("porous1").feature("pm1").set("poro_mat", "userdef");
    model.component("comp1").physics("ht").feature("porous1").feature("pm1").set("poro", "por");
    model.component("comp1").physics("ht").feature("porous1").feature("pm1")
         .set("porousMatrixPropertiesType", "solidPhaseProperties");
    model.component("comp1").physics("ht").feature("porous1").feature("pm1").set("k_sp_mat", "userdef");
    model.component("comp1").physics("ht").feature("porous1").feature("pm1")
         .set("k_sp", new String[]{"k_solid", "0", "0", "0", "k_solid", "0", "0", "0", "k_solid"});
    model.component("comp1").physics("ht").feature("porous1").feature("pm1").set("rho_sp_mat", "userdef");
    model.component("comp1").physics("ht").feature("porous1").feature("pm1").set("rho_sp", "rho_solid");
    model.component("comp1").physics("ht").feature("porous1").feature("pm1").set("Cp_sp_mat", "userdef");
    model.component("comp1").physics("ht").feature("porous1").feature("pm1").set("Cp_sp", "Cv/rho_solid");
    model.component("comp1").physics("ht").feature("init1").set("Tinit", "T_init");
    model.component("comp1").physics("ht").create("temp1", "TemperatureBoundary", 0);
    model.component("comp1").physics("ht").feature("temp1").selection().set(1);
    model.component("comp1").physics("ht").feature("temp1").set("T0", "T_in");

    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("elemcount", 100);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("elemratio", 10);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tunit", "h");
    model.study("std1").feature("time").set("tlist", "0 24 48 72");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("maxstepconstraintbdf", "const");
    model.sol("sol1").feature("t1").set("maxstepbdf", "2[min]");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").label("\u6e29\u5ea6 (ht)");
    model.result("pg1").set("showlooplevelinput", new String[]{"off", "off", "off"});
    model.result("pg1").feature().create("lngr1", "LineGraph");
    model.result("pg1").feature("lngr1").set("xdata", "expr");
    model.result("pg1").feature("lngr1").set("xdataexpr", "x");
    model.result("pg1").feature("lngr1").set("data", "parent");
    model.result("pg1").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg1").feature("lngr1").selection().set(1);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevelinput", "manual", 0);
    model.result("pg1").setIndex("looplevel", new int[]{2, 3, 4}, 0);
    model.result("pg1").set("legendlayout", "outside");
    model.result("pg1").set("legendposoutside", "bottom");
    model.result("pg1").run();
    model.result("pg1").feature("lngr1").set("unit", "degC");
    model.result("pg1").feature("lngr1").set("legend", true);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").importData("phase_change_lunardini_analytical_solution.txt");
    model.result("pg1").run();
    model.result("pg1").create("tblp1", "Table");
    model.result("pg1").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg1").feature("tblp1").set("linewidth", "preference");
    model.result("pg1").feature("tblp1").set("linestyle", "none");
    model.result("pg1").feature("tblp1").set("linecolor", "cyclereset");
    model.result("pg1").feature("tblp1").set("linemarker", "asterisk");
    model.result("pg1").feature("tblp1").set("markerpos", "interp");
    model.result("pg1").feature("tblp1").set("markers", 25);
    model.result("pg1").run();
    model.result("pg1").set("axislimits", true);
    model.result("pg1").set("xmax", 5);
    model.result("pg1").run();

    model.title("\u534a\u65e0\u9650\u571f\u67f1\u7684\u76f8\u53d8");

    model
         .description("\u672c\u4f8b\u6c42\u89e3\u591a\u5b54\u6750\u6599\u4e2d\u7684\u76f8\u53d8\u70ed\u4f20\u5bfc\u95ee\u9898\uff0c\u5e76\u5c06\u7ed3\u679c\u4e0e\u89e3\u6790\u89e3\uff08\u4e5f\u79f0\u4e3a Lunardini \u89e3\uff09\u8fdb\u884c\u6bd4\u8f83\u3002");

    model.label("phase_change_lunardini.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
