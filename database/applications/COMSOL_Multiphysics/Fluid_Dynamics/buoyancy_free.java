/*
 * buoyancy_free.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:34 by COMSOL 6.3.0.290. */
public class buoyancy_free {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\COMSOL_Multiphysics\\Fluid_Dynamics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");
    model.component("comp1").physics().create("ht", "HeatTransferInFluids", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/spf", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ht", true);

    model.param().set("Tc", "0");
    model.param().descr("Tc", "\u4f4e\u6e29");
    model.param().set("Th", "1");
    model.param().descr("Th", "\u9ad8\u6e29");
    model.param().set("Pr", "0.71");
    model.param().descr("Pr", "\u666e\u6717\u7279\u6570");
    model.param().set("p0", "0");
    model.param().descr("p0", "\u53c2\u8003\u538b\u529b");
    model.param().set("Ra", "0");
    model.param().descr("Ra", "\u745e\u5229\u6570");

    model.component("comp1").geom("geom1").create("sq1", "Square");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("spf").feature("fp1").set("rho_mat", "userdef");
    model.component("comp1").physics("spf").feature("fp1").set("rho", 1);
    model.component("comp1").physics("spf").feature("fp1").set("mu_mat", "userdef");
    model.component("comp1").physics("spf").feature("fp1").set("mu", 1);
    model.component("comp1").physics("spf").feature("init1").set("p_init", "p0");
    model.component("comp1").physics("spf").create("vf1", "VolumeForce", 2);
    model.component("comp1").physics("spf").feature("vf1").set("F", new String[]{"0", "(Ra/Pr)*(T-Tc)", "0"});
    model.component("comp1").physics("spf").create("prpc1", "PressurePointConstraint", 0);
    model.component("comp1").physics("spf").feature("prpc1").selection().set(2);
    model.component("comp1").physics("spf").feature("prpc1").set("p0", "p0");
    model.component("comp1").physics("spf").feature("vf1").selection().all();
    model.component("comp1").physics("ht").feature("fluid1").set("minput_pressure_src", "userdef");
    model.component("comp1").physics("ht").feature("fluid1").set("minput_pressure", "p0");
    model.component("comp1").physics("ht").feature("fluid1").set("u_src", "root.comp1.u");
    model.component("comp1").physics("ht").feature("fluid1").set("k_mat", "userdef");
    model.component("comp1").physics("ht").feature("fluid1").set("k", new int[]{1, 0, 0, 0, 1, 0, 0, 0, 1});
    model.component("comp1").physics("ht").feature("fluid1").set("fluidType", "gasLiquid");
    model.component("comp1").physics("ht").feature("fluid1").set("rho_mat", "userdef");
    model.component("comp1").physics("ht").feature("fluid1").set("rho", 1);
    model.component("comp1").physics("ht").feature("fluid1").set("gamma_not_IG_mat", "userdef");
    model.component("comp1").physics("ht").feature("fluid1").set("Cp_mat", "userdef");
    model.component("comp1").physics("ht").feature("fluid1").set("Cp", "Pr");
    model.component("comp1").physics("ht").feature("init1").set("Tinit", "Tc");
    model.component("comp1").physics("ht").create("temp1", "TemperatureBoundary", 1);
    model.component("comp1").physics("ht").feature("temp1").selection().set(1);
    model.component("comp1").physics("ht").feature("temp1").set("T0", "Tc");
    model.component("comp1").physics("ht").create("temp2", "TemperatureBoundary", 1);
    model.component("comp1").physics("ht").feature("temp2").selection().set(4);
    model.component("comp1").physics("ht").feature("temp2").set("T0", "Th");

    model.component("comp1").mesh("mesh1").autoMeshSize(2);
    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").selection().set(1, 4);
    model.component("comp1").mesh("mesh1").feature("edg1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("hauto", 1);
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "Tc", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "", 0);
    model.study("std1").feature("stat").setIndex("pname", "Tc", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "", 0);
    model.study("std1").feature("stat").setIndex("pname", "Ra", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "1 1e1 1e2 1e3 1e4 1e5 1e6", 0);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u901f\u5ea6 (spf)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u538b\u529b (spf)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").feature().create("con1", "Contour");
    model.result("pg2").feature("con1").label("\u7b49\u503c\u7ebf");
    model.result("pg2").feature("con1").set("showsolutionparams", "on");
    model.result("pg2").feature("con1").set("expr", "p");
    model.result("pg2").feature("con1").set("number", 40);
    model.result("pg2").feature("con1").set("levelrounding", false);
    model.result("pg2").feature("con1").set("smooth", "internal");
    model.result("pg2").feature("con1").set("showsolutionparams", "on");
    model.result("pg2").feature("con1").set("data", "parent");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u6e29\u5ea6 (ht)");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("solutionparams", "parent");
    model.result("pg3").feature("surf1").set("expr", "T");
    model.result("pg3").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").create("arws1", "ArrowSurface");
    model.result("pg3").feature("arws1").set("scaleactive", true);
    model.result("pg3").feature("arws1").set("scale", "2e-4");
    model.result("pg3").feature("arws1").set("color", "black");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").create("con1", "Contour");
    model.result("pg3").feature("con1").set("expr", "u");
    model.result("pg3").feature("con1").set("descr", "\u901f\u5ea6\u573a\uff0cx \u5206\u91cf");
    model.result("pg3").feature("con1").set("coloring", "uniform");
    model.result("pg3").feature("con1").set("color", "gray");
    model.result("pg3").feature("con1").set("colorlegend", false);
    model.result("pg3").run();
    model.result("pg3").label("\u6d6e\u529b");
    model.result("pg3").setIndex("looplevel", 4, 0);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 5, 0);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 6, 0);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 7, 0);
    model.result("pg3").run();

    model.title("\u81ea\u7531\u6d41\u4f53\u4e2d\u7684\u6d6e\u529b\u6d41");

    model
         .description("\u5bc6\u5ea6\u9a71\u52a8\u6d41\u6216\u6d6e\u529b\u6d41\u80fd\u5f71\u54cd\u7ba1\u9053\u3001\u6e56\u6cca\u3001\u6cb3\u6d41\u548c\u5c0f\u6eaa\u5185\u6d41\u4f53\u7684\u81ea\u7531\u6d41\u52a8\u3002\u672c\u4f8b\u6f14\u793a\u4e00\u4e2a\u6709\u6548\u65b9\u6cd5\uff0c\u5bf9\u7531\u5fae\u5c0f\u7684\u5bc6\u5ea6\u5dee\u5f02\u9020\u6210\u7684\u81ea\u7531\u6d41\u4f53\u6d6e\u529b\u6d41\u5efa\u6a21\u3002\u7814\u7a76\u7ed3\u679c\u4e0e de Vahl Davis \u5df2\u51fa\u7248\u7684\u8457\u4f5c\u4e00\u81f4\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("buoyancy_free.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
