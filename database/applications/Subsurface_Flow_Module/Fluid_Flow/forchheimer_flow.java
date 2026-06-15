/*
 * forchheimer_flow.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:28 by COMSOL 6.3.0.290. */
public class forchheimer_flow {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Subsurface_Flow_Module\\Fluid_Flow");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("fp", "FreeAndPorousMediaFlow", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/fp", true);

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"1e-3", "6e-3"});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"0", "-3e-3"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"1e-3", "8e-3"});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"-1e-3", "-4e-3"});
    model.component("comp1").geom("geom1").run("fin");

    model.param().set("v0", "2[cm/s]");
    model.param().descr("v0", "\u5165\u53e3\u901f\u5ea6");
    model.param().set("eps_p", "0.4");
    model.param().descr("eps_p", "\u5b54\u9699\u7387");
    model.param().set("Cf", "1.75/sqrt(150*eps_p^3)");
    model.param().descr("Cf", "\u6469\u64e6\u7cfb\u6570");
    model.param().set("fs", "1");
    model.param().descr("fs", "\u8f6c\u6362\u4e3a Forchheimer \u9879");

    model.component("comp1").physics("fp").create("porous1", "PorousMedium", 2);
    model.component("comp1").physics("fp").feature("porous1").selection().set(2);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u6d41\u4f53");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"1000[kg/m^3]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("dynamicviscosity", new String[]{"1e-3[Pa*s]"});
    model.component("comp1").material().create("pmat1", "PorousMedia");
    model.component("comp1").material("pmat1").selection().set(2);
    model.component("comp1").material("pmat1").set("porosity", "eps_p");
    model.component("comp1").material("pmat1").propertyGroup("def")
         .set("hydraulicpermeability", new String[]{"1e-7"});
    model.component("comp1").material("pmat1").feature().create("fluid1", "Fluid", "comp1");
    model.component("comp1").material("pmat1").feature("fluid1").set("link", "mat1");

    model.component("comp1").physics("fp").feature("porous1").set("flowModelType", "nonDarcian");
    model.component("comp1").physics("fp").feature("porous1").feature("pm1").set("cf", "fs*Cf");
    model.component("comp1").physics("fp").create("inl1", "InletBoundary", 1);
    model.component("comp1").physics("fp").feature("inl1").selection().set(2);
    model.component("comp1").physics("fp").feature("inl1").set("U0in", "v0");
    model.component("comp1").physics("fp").create("out1", "OutletBoundary", 1);
    model.component("comp1").physics("fp").feature("out1").selection().set(3);

    model.component("comp1").mesh("mesh1").autoMeshSize(4);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "v0", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m/s", 0);
    model.study("std1").feature("stat").setIndex("pname", "v0", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m/s", 0);
    model.study("std1").feature("stat").setIndex("pname", "fs", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "0 1", 0);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u901f\u5ea6 (fp)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u538b\u529b (fp)");
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
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").create("str1", "Streamline");
    model.result("pg1").feature("str1").set("selnumber", 8);
    model.result("pg1").feature("str1").selection().set(2);
    model.result("pg1").feature("str1").set("pointtype", "arrow");
    model.result("pg1").feature("str1").set("arrowdistr", "equaltime");
    model.result("pg1").feature("str1").set("color", "white");
    model.result("pg1").run();
    model.result("pg1").set("plotarrayenable", true);
    model.result("pg1").feature("str1").set("arraydim", "1");
    model.result("pg1").run();
    model.result("pg1").feature("str1").set("manualindexing", true);
    model.result("pg1").feature("surf1").set("arraydim", "1");
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("surf2", "surf1");
    model.result("pg1").feature().duplicate("str2", "str1");
    model.result("pg1").feature("surf2").set("arraydim", "1");
    model.result("pg1").run();
    model.result("pg1").feature("surf2").set("data", "dset1");
    model.result("pg1").feature("surf2").set("inheritplot", "surf1");
    model.result("pg1").feature("str2").set("arraydim", "1");
    model.result("pg1").run();
    model.result("pg1").feature("str2").set("data", "dset1");
    model.result("pg1").feature("str2").set("arrayindex", 1);
    model.result("pg1").run();
    model.result().dataset().create("cln1", "CutLine2D");
    model.result().dataset("cln1").setIndex("genpoints", "-1e3", 0, 0);
    model.result().dataset("cln1").setIndex("genpoints", "1e3", 1, 0);
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u901f\u5ea6\u5927\u5c0f");
    model.result("pg3").create("lngr1", "LineGraph");
    model.result("pg3").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg3").feature("lngr1").set("linewidth", "preference");
    model.result("pg3").feature("lngr1").set("data", "cln1");
    model.result("pg3").run();
    model.result("pg3").feature("lngr1").set("linemarker", "cycle");
    model.result("pg3").feature("lngr1").set("markerpos", "interp");
    model.result("pg3").feature("lngr1").set("legend", true);
    model.result("pg3").feature("lngr1").set("legendprefix", "fs=");
    model.result("pg3").run();
    model.result("pg1").run();

    model.title("Forchheimer \u6d41\u52a8");

    model
         .description("\u5728\u5f00\u653e\u7684\u591a\u5b54\u7ed3\u6784\uff08\u5982\u586b\u5145\u5e8a\u7b49\uff09\u4e2d\uff0c\u6d41\u4f53\u6d41\u52a8\u6240\u53d7\u7684\u963b\u529b\u540c\u65f6\u53d7\u5c42\u6d41\u548c\u6e4d\u6d41\u6548\u5e94\u7684\u5f71\u54cd\u3002\u672c\u4f8b\u4e2d\u6c42\u89e3\u7684 Forchheimer \u65b9\u7a0b\u540c\u65f6\u8003\u8651\u4e86\u8fd9\u4e24\u4e2a\u6548\u5e94\u3002");

    model.label("forchheimer_flow.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
