/*
 * aquifer_water_table.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:31 by COMSOL 6.3.0.290. */
public class aquifer_water_table {

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
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/dl", true);
    model.study("std1").feature("stat").setSolveFor("/physics/tds", true);

    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 0, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 0, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 250, 1, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 1, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 250, 2, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 5.35, 2, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 180, 3, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 5.575, 3, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 155, 4, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 6.045, 4, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 127, 5, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 6.455, 5, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 80, 6, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 6.603, 6, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 7, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 6.645, 7, 1);
    model.component("comp1").geom("geom1").run("pol1");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new int[]{120, 2});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new int[]{0, 2});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new int[]{70, 2});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new int[]{180, 2});
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").view().create("view2", "geom1");
    model.component("comp1").view("view2").axis().set("viewscaletype", "automatic");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("dl").feature("porous1").feature("pm1")
         .set("permeabilityModelType", "conductivity");
    model.component("comp1").physics("dl").feature("porous1").feature("pm1")
         .set("K", new String[]{"5e-4[cm/s]", "0", "0", "0", "5e-4[cm/s]", "0", "0", "0", "5e-4[cm/s]"});
    model.component("comp1").physics("dl").feature().duplicate("porous2", "porous1");
    model.component("comp1").physics("dl").feature("porous2").selection().set(2, 3);
    model.component("comp1").physics("dl").feature("porous2").feature("pm1")
         .set("K", new String[]{"1e-2[cm/s]", "0", "0", "0", "1e-2[cm/s]", "0", "0", "0", "1e-2[cm/s]"});
    model.component("comp1").physics("dl").create("inl1", "Inlet", 1);
    model.component("comp1").physics("dl").feature("inl1").selection().set(7, 8, 10, 11, 15);
    model.component("comp1").physics("dl").feature("inl1").set("U0in", "10[cm/a]");
    model.component("comp1").physics("dl").create("hh1", "HydraulicHead", 1);
    model.component("comp1").physics("dl").feature("hh1").selection().set(16, 17, 18);
    model.component("comp1").physics("dl").feature("hh1").set("H0", 5.3486);
    model.component("comp1").physics("dl").create("sym1", "Symmetry", 1);
    model.component("comp1").physics("dl").feature("sym1").selection().set(1, 3, 5);
    model.component("comp1").physics("tds").feature("porous1").feature("fluid1").set("u_src", "root.comp1.dl.u");
    model.component("comp1").physics("tds").feature("porous1").feature("fluid1")
         .set("DF_c", new String[]{"1.34e-5[cm^2/s]", "0", "0", "0", "1.34e-5[cm^2/s]", "0", "0", "0", "1.34e-5[cm^2/s]"});
    model.component("comp1").physics("tds").feature("porous1").create("disp1", "Dispersion", 2);
    model.component("comp1").physics("tds").feature("porous1").feature("disp1")
         .set("DispersionTensor", "Dispersivity");
    model.component("comp1").physics("tds").feature("porous1").feature("disp1").set("alphaL", 0.5);
    model.component("comp1").physics("tds").feature("porous1").feature("disp1").set("alphaT", 0.005);

    model.component("comp1").func().create("rect1", "Rectangle");
    model.component("comp1").func("rect1").set("lower", 40);
    model.component("comp1").func("rect1").set("upper", 80);

    model.component("comp1").physics("tds").create("conc1", "Concentration", 1);
    model.component("comp1").physics("tds").feature("conc1").selection().set(7, 8, 10, 11, 15);
    model.component("comp1").physics("tds").feature("conc1").setIndex("species", true, 0);
    model.component("comp1").physics("tds").feature("conc1").setIndex("c0", "rect1(x[1/m])*(t<=5[a])", 0);
    model.component("comp1").physics("tds").create("conc2", "Concentration", 1);
    model.component("comp1").physics("tds").feature("conc2").selection().set(1, 3, 5);
    model.component("comp1").physics("tds").feature("conc2").setIndex("species", true, 0);
    model.component("comp1").physics("tds").create("out1", "Outflow", 1);
    model.component("comp1").physics("tds").feature("out1").selection().set(2, 16, 17, 18);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"1000"});
    model.component("comp1").material("mat1").propertyGroup("def").set("porosity", new String[]{"0.35"});

    model.component("comp1").mesh("mesh1").autoMeshSize(1);
    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", 0.5);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").setSolveFor("/physics/tds", false);
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/dl", false);
    model.study("std1").feature("time").set("tunit", "a");
    model.study("std1").feature("time").set("tlist", "range(0,1,20)");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");

    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u538b\u529b (dl)");
    model.result("pg1").set("titletype", "custom");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").feature().create("str1", "Streamline");
    model.result("pg1").feature("str1").set("posmethod", "uniform");
    model.result("pg1").feature("str1").set("pointtype", "arrow");
    model.result("pg1").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg1").feature("str1").set("color", "gray");
    model.result("pg1").feature("str1").set("smooth", "internal");
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("data", "parent");
    model.result("pg1").feature("str1").selection().geom("geom1", 1);
    model.result("pg1").feature("str1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18);
    model.result("pg1").label("\u538b\u529b (dl)");
    model.result("pg1").run();
    model.result("pg1").label("\u6c34\u5934");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("expr", "dl.H");
    model.result("pg1").run();
    model.result("pg1").feature("str1").set("posmethod", "selection");
    model.result("pg1").feature("str1").selection().set(7, 8, 10, 11, 15);
    model.result("pg1").feature("str1").set("selnumber", 15);
    model.result("pg1").feature("str1").set("arrowlength", "normalized");
    model.result("pg1").feature("str1").set("arrowscaleactive", true);
    model.result("pg1").feature("str1").set("arrowscale", "3e7");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 21, 0);
    model.result("pg2").label("\u6d53\u5ea6 (tds)");
    model.result("pg2").set("titletype", "custom");
    model.result("pg2").set("prefixintitle", "");
    model.result("pg2").set("expressionintitle", false);
    model.result("pg2").set("typeintitle", true);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"c"});
    model.result("pg2").feature("surf1").set("colortable", "Prism");
    model.result("pg2").create("arws1", "ArrowSurface");
    model.result("pg2").feature("arws1").set("expr", new String[]{"tds.tflux_cx", "tds.tflux_cy"});
    model.result("pg2").feature("arws1").set("xnumber", 10);
    model.result("pg2").feature("arws1").set("ynumber", 10);
    model.result("pg2").feature("arws1").set("color", "black");
    model.result("pg2").feature("arws1").create("sel1", "Selection");
    model.result("pg2").feature("arws1").feature("sel1").selection().set(1, 2, 3);
    model.result("pg2").label("\u6d53\u5ea6 (tds)");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("colortabletype", "discrete");
    model.result("pg2").feature("surf1").set("bandcount", 12);
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 13, 0);
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 9, 0);
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 21, 0);
    model.result("pg2").run();
    model.result().dataset().create("extr1", "Extrude2D");
    model.result().dataset("extr1").set("zmax", "50");
    model.result().dataset("extr1").set("planemap", "xz");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").run();
    model.result("pg3").create("iso1", "Isosurface");
    model.result("pg3").feature("iso1").set("expr", "c");
    model.result("pg3").feature("iso1").set("number", 12);
    model.result("pg3").feature("iso1").set("colortable", "Prism");
    model.result("pg3").feature("iso1").set("colorlegend", false);
    model.result("pg3").run();
    model.result("pg3").create("vol1", "Volume");
    model.result("pg3").feature("vol1").set("coloring", "uniform");
    model.result("pg3").feature("vol1").set("color", "gray");
    model.result("pg3").feature("vol1").create("mtrl1", "MaterialAppearance");
    model.result("pg3").run();
    model.result("pg3").feature("vol1").feature("mtrl1").set("useplotcolors", true);
    model.result("pg3").feature("vol1").feature("mtrl1").set("appearance", "custom");
    model.result("pg3").feature("vol1").feature("mtrl1").set("family", "soil");
    model.result("pg3").run();
    model.result("pg3").feature("vol1").create("tran1", "Transparency");
    model.result("pg3").run();
    model.result("pg3").feature("vol1").feature("tran1").set("uniformblending", 0.3);
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg2").run();

    model.title("\u542b\u6c34\u5c42\u6c34\u4f4d\u8ba1\u7b97");

    model
         .description("\u672c\u6a21\u578b\u6f14\u793a\u5982\u4f55\u5c06 COMSOL Multiphysics \u5e94\u7528\u4e8e\u6cbf\u65e0\u538b\u542b\u6c34\u5c42\u5782\u76f4\u6a2a\u622a\u9762\u7684\u7a33\u6001\u5730\u4e0b\u6d41\u4f53\u6d41\u52a8\u548c\u77ac\u6001\u6eb6\u8d28\u8fd0\u79fb\u7684\u57fa\u51c6\u6848\u4f8b\u3002\u6eb6\u8d28\u8fd0\u79fb\u53d7\u5230\u5f3a\u70c8\u5404\u5411\u5f02\u6027\u5f25\u6563\u7684\u9ad8\u5ea6\u4e0d\u89c4\u5219\u6d41\u52a8\u6761\u4ef6\u7684\u5f71\u54cd\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("aquifer_water_table.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
