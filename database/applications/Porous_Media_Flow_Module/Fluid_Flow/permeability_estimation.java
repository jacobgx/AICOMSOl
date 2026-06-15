/*
 * permeability_estimation.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:11 by COMSOL 6.3.0.290. */
public class permeability_estimation {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Porous_Media_Flow_Module\\Fluid_Flow");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("spf", "CreepingFlow", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/spf", true);

    model.param().set("dp1", "0.2[mm]");
    model.param().descr("dp1", "\u76f4\u5f84\uff0c\u7403\u4f53 1");
    model.param().set("dp2", "0.4[mm]");
    model.param().descr("dp2", "\u76f4\u5f84\uff0c\u7403\u4f53 2");
    model.param().set("dist", "dp1/10");
    model.param().descr("dist", "\u7403\u4f53\u95f4\u8ddd");
    model.param().set("L", "2*dp1+dist");
    model.param().descr("L", "\u57fa\u672c\u5355\u5143\u7684\u8fb9\u957f");

    model.geom()
         .load(new String[]{"part1"}, "COMSOL_Multiphysics\\Unit_Cells_and_RVEs\\Particulate_Composites\\particulate_body_centered_cubic.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "dp1", "dp1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "sp", "dp2/dp1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "wm", "L");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "dm", "L");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "hm", "L");
    model.component("comp1").geom("geom1").run("pi1");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").init(3);
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("pi1", 1, 3, 4, 5, 6, 7, 8, 9, 10);
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").view("view1").set("transparency", false);

    model.param().set("p_in", "1[Pa]");
    model.param().descr("p_in", "\u5165\u53e3\u538b\u529b");
    model.param().set("rho_f", "1000[kg/m^3]");
    model.param().descr("rho_f", "\u6d41\u4f53\u5bc6\u5ea6");
    model.param().set("mu_f", "1e-3[Pa*s]");
    model.param().descr("mu_f", "\u6d41\u4f53\u9ecf\u5ea6");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"rho_f"});
    model.component("comp1").material("mat1").propertyGroup("def").set("dynamicviscosity", new String[]{"mu_f"});

    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 2);
    model.component("comp1").physics("spf").feature("inl1").set("BoundaryCondition", "Pressure");
    model.component("comp1").physics("spf").feature("inl1").set("p0", "p_in");
    model.component("comp1").physics("spf").feature("inl1").selection().set(6);
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 2);
    model.component("comp1").physics("spf").feature("out1").selection().set(5);
    model.component("comp1").physics("spf").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("spf").feature("sym1").selection().set(1, 2, 9, 22);

    model.component("comp1").massProp().create("mass1", "MassProperties");
    model.component("comp1").massProp("mass1").set("createMass", false);
    model.component("comp1").massProp("mass1").set("createCenterOfMass", false);
    model.component("comp1").massProp("mass1").set("createMomentOfInertia", false);
    model.component("comp1").massProp("mass1").set("createPrincipalInertia", false);

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("por", "mass1.volume/L^3");
    model.component("comp1").variable("var1").descr("por", "\u5b54\u9699\u7387");
    model.component("comp1").variable("var1").set("u_out", "spf.out1.Mflow/rho_f/L^2");
    model.component("comp1").variable("var1").descr("u_out", "\u51fa\u53e3\u901f\u5ea6");
    model.component("comp1").variable("var1").set("k0", "u_out*mu_f*L/p_in");
    model.component("comp1").variable("var1").descr("k0", "\u6e17\u900f\u7387");

    model.component("comp1").mesh("mesh1").autoMeshSize(4);
    model.component("comp1").mesh("mesh1").contribute("geom/detail", false);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u901f\u5ea6 (spf)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("slc1", "Slice");
    model.result("pg1").feature("slc1").label("\u5207\u9762");
    model.result("pg1").feature("slc1").set("smooth", "internal");
    model.result("pg1").feature("slc1").set("showsolutionparams", "on");
    model.result("pg1").feature("slc1").set("data", "parent");
    model.result("pg1").label("\u901f\u5ea6 (spf)");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("slc1").set("quickxnumber", 1);
    model.result("pg1").feature("slc1").set("colortable", "JupiterAuroraBorealis");
    model.result("pg1").feature().duplicate("slc2", "slc1");
    model.result("pg1").run();
    model.result("pg1").feature("slc2").set("quickplane", "zx");
    model.result("pg1").feature("slc2").set("quickynumber", 1);
    model.result("pg1").feature("slc2").set("inheritplot", "slc1");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", "1");
    model.result("pg1").feature("surf1").set("coloring", "uniform");
    model.result("pg1").feature("surf1").set("color", "gray");
    model.result("pg1").feature("surf1").create("sel1", "Selection");
    model.result("pg1").feature("surf1").feature("sel1").selection().all();
    model.result("pg1").feature("surf1").feature("sel1").selection()
         .set(5, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22);
    model.result("pg1").run();
    model.result("pg1").create("str1", "Streamline");
    model.result("pg1").feature("str1").selection().set(6);
    model.result("pg1").feature("str1").set("selnumber", 40);
    model.result("pg1").feature("str1").set("pointtype", "arrow");
    model.result("pg1").feature("str1").set("arrowcountactive", true);
    model.result("pg1").feature("str1").set("arrowcount", 80);
    model.result("pg1").run();
    model.result("pg1").feature("str1").create("col1", "Color");
    model.result("pg1").run();
    model.result("pg1").feature("str1").feature("col1").set("expr", "p");
    model.result("pg1").feature("str1").feature("col1").set("colortable", "GrayPrint");
    model.result("pg1").run();
    model.result("pg1").set("titletype", "none");
    model.result("pg1").set("edges", false);
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").run();
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").set("expr", new String[]{"k0"});
    model.result().numerical("gev1").set("descr", new String[]{"\u6e17\u900f\u7387"});
    model.result().numerical("gev1").set("expr", new String[]{"k0", "por"});
    model.result().numerical("gev1").set("descr", new String[]{"\u6e17\u900f\u7387", "\u5b54\u9699\u7387"});
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u5168\u5c40\u8ba1\u7b97 1");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom2", 3);
    model.component("comp2").geom("geom2").geomRep("comsol");

    model.component("comp2").mesh().create("mesh2");
    model.component("comp2").mesh("mesh2").contribute("geom/detail", true);

    model.component("comp2").geom("geom2").create("blk1", "Block");
    model.component("comp2").geom("geom2").feature("blk1").set("size", new String[]{"3*L", "3*L", "10*L"});
    model.component("comp2").geom("geom2").runPre("fin");

    model.component("comp2").physics().create("dl", "PorousMediaFlowDarcy", "geom2");

    model.study("std1").feature("stat").setSolveFor("/physics/dl", false);

    model.component("comp2").geom("geom2").run();

    model.component("comp2").physics("dl").feature("porous1").feature("fluid1").set("rho_mat", "userdef");
    model.component("comp2").physics("dl").feature("porous1").feature("fluid1").set("rho", "rho_f");
    model.component("comp2").physics("dl").feature("porous1").feature("fluid1").set("mu_mat", "userdef");
    model.component("comp2").physics("dl").feature("porous1").feature("fluid1").set("mu", "mu_f");
    model.component("comp2").physics("dl").feature("porous1").feature("pm1").set("epsilon_mat", "userdef");
    model.component("comp2").physics("dl").feature("porous1").feature("pm1").set("epsilon", 0.494);
    model.component("comp2").physics("dl").feature("porous1").feature("pm1").set("kappa_mat", "userdef");
    model.component("comp2").physics("dl").feature("porous1").feature("pm1")
         .set("kappa", new double[]{2.998E-10, 0, 0, 0, 2.998E-10, 0, 0, 0, 2.998E-10});
    model.component("comp2").physics("dl").create("pr1", "Pressure", 2);
    model.component("comp2").physics("dl").feature("pr1").selection().set(4);
    model.component("comp2").physics("dl").feature("pr1").set("p0", "10*p_in");
    model.component("comp2").physics("dl").create("pr2", "Pressure", 2);
    model.component("comp2").physics("dl").feature("pr2").selection().set(3);

    model.component("comp2").mesh("mesh2").contribute("geom/detail", false);
    model.component("comp2").mesh("mesh2").run();

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/spf", false);
    model.study("std2").feature("stat").setSolveFor("/physics/dl", true);
    model.study("std2").setGenPlots(false);
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u538b\u529b (dl)");
    model.result("pg2").set("data", "dset3");
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").label("\u8868\u9762");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg2").label("\u538b\u529b (dl)");
    model.result("pg2").run();
    model.result("pg2").create("arws1", "ArrowSurface");
    model.result("pg2").feature("arws1").set("titletype", "none");
    model.result("pg2").feature("arws1").set("color", "white");
    model.result("pg2").run();
    model.result().numerical().create("gev2", "EvalGlobal");
    model.result().numerical("gev2").set("expr", new String[]{"spf.out1.massFlowRate"});
    model.result().numerical("gev2")
         .set("descr", new String[]{"\u6574\u4e2a\u7279\u5f81\u9009\u62e9\u7684\u5411\u5916\u8d28\u91cf\u6d41\u7387"});
    model.result().numerical("gev2").set("unit", new String[]{"kg/s"});
    model.result().numerical("gev2").setIndex("expr", "spf.out1.massFlowRate/L^2", 0);
    model.result().numerical("gev2").setIndex("descr", "\u5355\u4f4d\u8d28\u91cf\u6d41", 0);
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("\u5168\u5c40\u8ba1\u7b97 2");
    model.result().numerical("gev2").set("table", "tbl2");
    model.result().numerical("gev2").setResult();
    model.result().numerical().create("gev3", "EvalGlobal");
    model.result().numerical("gev3").set("data", "dset3");
    model.result().numerical("gev3").set("expr", new String[]{"dl.pr2.Mflow"});
    model.result().numerical("gev3").set("descr", new String[]{"\u8d28\u91cf\u6d41"});
    model.result().numerical("gev3").set("unit", new String[]{"kg/s"});
    model.result().numerical("gev3").setIndex("expr", "dl.pr2.Mflow/(3*L)^2", 0);
    model.result().numerical("gev3").setIndex("descr", "\u5355\u4f4d\u8d28\u91cf\u6d41", 0);
    model.result().numerical("gev3").set("table", "tbl2");
    model.result().numerical("gev3").appendResult();
    model.result("pg1").run();

    model.title("\u5fae\u578b\u591a\u5b54\u7ed3\u6784\u7684\u6e17\u900f\u7387\u8ba1\u7b97");

    model
         .description("\u672c\u6559\u7a0b\u6f14\u793a\u5982\u4f55\u901a\u8fc7\u6a21\u62df\u6d41\u7ecf\u591a\u5b54\u7ed3\u6784\u57fa\u672c\u5355\u5143\u7684\u8815\u52a8\u6d41\u6765\u8ba1\u7b97\u591a\u5b54\u6750\u6599\u7684\u6e17\u900f\u7387\u3002\u4eff\u771f\u7ed3\u679c\u968f\u540e\u53ef\u7528\u4e8e\u5728\u5b8f\u89c2\u4e0a\u6a21\u62df\u591a\u5b54\u6750\u6599\u4e2d\u7684\u6d41\u52a8\uff08\u4f8b\u5982\u9897\u7c92\u5e8a\u53cd\u5e94\u5668\u4e2d\u7684\u6d41\u52a8\uff09\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("permeability_estimation.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
