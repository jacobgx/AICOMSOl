/*
 * buoyancy_darcy_elder.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:32 by COMSOL 6.3.0.290. */
public class buoyancy_darcy_elder {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Subsurface_Flow_Module\\Solute_Transport");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("dl", "PorousMediaFlowDarcy", "geom1");
    model.component("comp1").physics().create("tds", "DilutedSpeciesInPorousMedia", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/dl", true);
    model.study("std1").feature("time").setSolveFor("/physics/tds", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("L", "150[m]", "\u6c60\u6df1\u5ea6");
    model.param().set("rho0", "1000[kg/m^3]", "\u7eaf\u51c0\u6c34\u5bc6\u5ea6");
    model.param().set("rho_s", "1200[kg/m^3]", "\u76d0\u6c34\u5bc6\u5ea6");
    model.param().set("c0", "0[mol/m^3]", "\u96f6\u76d0\u6d53\u5ea6");
    model.param().set("c_s", "1[mol/m^3]", "\u5f52\u4e00\u5316\u76d0\u6d53\u5ea6");
    model.param().set("beta", "(rho_s-rho0)/(c_s-c0)", "\u76d0\u6d53\u5ea6\u5bfc\u81f4\u5bc6\u5ea6\u589e\u52a0");
    model.param().set("p0", "0[atm]", "\u53c2\u8003\u538b\u529b");
    model.param().set("mu", "1e-3[Pa*s]", "\u52a8\u529b\u9ecf\u5ea6");
    model.param().set("kappa", "500[mD]", "\u6e17\u900f\u7387");
    model.param().set("epsilon", "0.1", "\u5b54\u9699\u7387");
    model.param().set("D_L", "3.56e-6[m^2/s]", "\u5206\u5b50\u6269\u6563");
    model.param().set("Pe", "beta*(c_s-c0)*g_const*kappa*L/(mu*epsilon*D_L)", "\u4f69\u514b\u83b1\u7279\u6570");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"2*L", "L"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", "L", 0);
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", "L", 1);
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1").set("rho", "rho0+beta*c*(c>0)");
    model.component("comp1").variable("var1").descr("rho", "\u6c34\u5bc6\u5ea6");

    model.component("comp1").physics("dl").prop("GravityEffects").set("IncludeGravity", true);
    model.component("comp1").physics("dl").feature("gr1").set("GravityType", "Elevation");
    model.component("comp1").physics("dl").feature("gr1").set("useRref", true);
    model.component("comp1").physics("dl").feature("gr1").set("rref", new String[]{"0", "L", "0"});
    model.component("comp1").physics("dl").feature("porous1").feature("fluid1").set("rho_mat", "userdef");
    model.component("comp1").physics("dl").feature("porous1").feature("fluid1").set("rho", "rho");
    model.component("comp1").physics("dl").feature("porous1").feature("fluid1").set("mu_mat", "userdef");
    model.component("comp1").physics("dl").feature("porous1").feature("fluid1").set("mu", "mu");
    model.component("comp1").physics("dl").feature("porous1").feature("pm1").set("epsilon_mat", "userdef");
    model.component("comp1").physics("dl").feature("porous1").feature("pm1").set("epsilon", "epsilon");
    model.component("comp1").physics("dl").feature("porous1").feature("pm1").set("kappa_mat", "userdef");
    model.component("comp1").physics("dl").feature("porous1").feature("pm1")
         .set("kappa", new String[]{"kappa", "0", "0", "0", "kappa", "0", "0", "0", "kappa"});
    model.component("comp1").physics("dl").feature("init1").set("InitType", "H");
    model.component("comp1").physics("dl").create("sym1", "Symmetry", 1);
    model.component("comp1").physics("dl").feature("sym1").selection().set(5);
    model.component("comp1").physics("dl").create("constr1", "PointwiseConstraint", 0);
    model.component("comp1").physics("dl").feature("constr1").set("constraintExpression", "p0-p");
    model.component("comp1").physics("dl").feature("constr1").selection().set(2);
    model.component("comp1").physics("tds").feature("porous1").feature("fluid1").set("u_src", "root.comp1.dl.u");
    model.component("comp1").physics("tds").feature("porous1").feature("fluid1")
         .set("DF_c", new String[]{"D_L", "0", "0", "0", "D_L", "0", "0", "0", "D_L"});
    model.component("comp1").physics("tds").feature("porous1").feature("fluid1")
         .set("FluidDiffusivityModelType", "TortuosityModel");
    model.component("comp1").physics("tds").feature("porous1").feature("pm1").set("poro_mat", "userdef");
    model.component("comp1").physics("tds").feature("porous1").feature("pm1").set("poro", "epsilon");
    model.component("comp1").physics("tds").feature("init1").setIndex("initc", "c0", 0);
    model.component("comp1").physics("tds").create("sym1", "Symmetry", 1);
    model.component("comp1").physics("tds").feature("sym1").selection().set(5);
    model.component("comp1").physics("tds").create("conc1", "Concentration", 1);
    model.component("comp1").physics("tds").feature("conc1").selection().set(2);
    model.component("comp1").physics("tds").feature("conc1").setIndex("species", true, 0);
    model.component("comp1").physics("tds").feature("conc1").setIndex("c0", "c0", 0);
    model.component("comp1").physics("tds").create("conc2", "Concentration", 1);
    model.component("comp1").physics("tds").feature("conc2").selection().set(4);
    model.component("comp1").physics("tds").feature("conc2").setIndex("species", true, 0);
    model.component("comp1").physics("tds").feature("conc2").setIndex("c0", "c_s", 0);

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 1);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tunit", "a");
    model.study("std1").feature("time").set("tlist", "range(0,1,20)");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("maxstepconstraintbdf", "const");
    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u538b\u529b (dl)");
    model.result("pg1").set("titletype", "custom");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").feature().create("str1", "Streamline");
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("posmethod", "uniform");
    model.result("pg1").feature("str1").set("pointtype", "arrow");
    model.result("pg1").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg1").feature("str1").set("color", "gray");
    model.result("pg1").feature("str1").set("smooth", "internal");
    model.result("pg1").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("data", "parent");
    model.result("pg1").feature("str1").selection().geom("geom1", 1);
    model.result("pg1").feature("str1").selection().set(1, 2, 3, 4, 5);
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 21, 0);
    model.result("pg2").label("\u6d53\u5ea6 (tds)");
    model.result("pg2").set("titletype", "custom");
    model.result("pg2").set("prefixintitle", "");
    model.result("pg2").set("expressionintitle", false);
    model.result("pg2").set("typeintitle", false);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"c"});
    model.result("pg2").feature("surf1").set("colortable", "Prism");
    model.result("pg2").set("typeintitle", true);
    model.result("pg2").create("arws1", "ArrowSurface");
    model.result("pg2").feature("arws1").set("expr", new String[]{"tds.tflux_cx", "tds.tflux_cy"});
    model.result("pg2").feature("arws1").set("xnumber", 10);
    model.result("pg2").feature("arws1").set("ynumber", 10);
    model.result("pg2").feature("arws1").set("color", "black");
    model.result("pg2").feature("arws1").create("sel1", "Selection");
    model.result("pg2").feature("arws1").feature("sel1").selection().set(1);
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").create("con1", "Contour");
    model.result("pg2").feature("con1").set("expr", "c");
    model.result("pg2").feature("con1").set("descr", "\u6469\u5c14\u6d53\u5ea6\uff0cc");
    model.result("pg2").feature("con1").set("colorlegend", false);
    model.result("pg2").feature("con1").set("coloring", "uniform");
    model.result("pg2").feature("con1").set("color", "black");
    model.result("pg2").run();
    model.result("pg2").feature("arws1").active(false);
    model.result("pg2").run();
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("title", "\u6d53\u5ea6 (kg/m<sup>3</sup>)");
    model.result("pg2").setIndex("looplevel", 2, 0);
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 3, 0);
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 4, 0);
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 11, 0);
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 16, 0);
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 21, 0);
    model.result("pg2").run();
    model.result().duplicate("pg3", "pg2");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature().remove("con1");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").create("str1", "Streamline");
    model.result("pg3").feature("str1").set("posmethod", "uniform");
    model.result("pg3").feature("str1").set("pointtype", "arrow");
    model.result("pg3").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg3").feature("str1").set("color", "gray");
    model.result("pg3").run();
    model.result("pg3").label("\u6d53\u5ea6\u548c\u901f\u5ea6");
    model.result("pg3").setIndex("looplevel", 4, 0);
    model.result("pg3")
         .set("title", "\u8868\u9762\uff1a\u6d53\u5ea6 (kg/m<sup>3</sup>) \u6d41\u7ebf\uff1a\u901f\u5ea6\u573a");
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 11, 0);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 16, 0);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 21, 0);
    model.result("pg3").run();

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/dl", true);
    model.study("std2").feature("stat").setSolveFor("/physics/tds", true);
    model.study("std2").setGenPlots(false);
    model.study("std2").feature("stat").set("useinitsol", true);
    model.study("std2").feature("stat").set("initmethod", "sol");
    model.study("std2").feature("stat").set("initstudy", "std1");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result("pg2").run();
    model.result().duplicate("pg4", "pg2");
    model.result("pg4").run();
    model.result("pg4").label("\u6d53\u5ea6\uff0c\u7a33\u6001");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").run();

    model.title("\u4f7f\u7528\u8fbe\u897f\u5b9a\u5f8b\u5206\u6790\u6d6e\u529b\u6d41 - Elder \u95ee\u9898");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u6a21\u62df\u6e29\u5ea6\u6216\u6d53\u5ea6\u5f15\u8d77\u7684\u5bc6\u5ea6\u53d8\u5316\u5728\u591a\u5b54\u4ecb\u8d28\u4e2d\u4ea7\u751f\u7684\u6d6e\u529b\u6d41\u3002\u8be5\u6a21\u578b\u91c7\u7528 Voss \u548c Souza \u7684\u5efa\u6a21\u65b9\u6cd5\u8fdb\u884c Elder \u7684\u5b9e\u9a8c\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("buoyancy_darcy_elder.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
