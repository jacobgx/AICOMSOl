/*
 * electric_shielding_comparison.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:23 by COMSOL 6.3.0.290. */
public class electric_shielding_comparison {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Introductory_Electric_Currents");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("ec", "ConductiveMedia", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/ec", true);

    model.param().set("sigma_1", "1[S/m]");
    model.param().descr("sigma_1", "\u7535\u5bfc\u7387\uff0c\u6750\u6599 1");
    model.param().set("sigma_2", "1[S/m]");
    model.param().descr("sigma_2", "\u7535\u5bfc\u7387\uff0c\u6750\u6599 2");

    model.component("comp1").geom("geom1").create("sq1", "Square");
    model.component("comp1").geom("geom1").feature("sq1").set("pos", new double[]{0.05, 0});
    model.component("comp1").geom("geom1").run("sq1");
    model.component("comp1").geom("geom1").create("sq2", "Square");
    model.component("comp1").geom("geom1").feature("sq2").set("pos", new double[]{-1.05, 0});
    model.component("comp1").geom("geom1").run("sq2");
    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", 0.245);
    model.component("comp1").geom("geom1").feature("c1").set("pos", new double[]{0.55, 0.5});
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("c2", "Circle");
    model.component("comp1").geom("geom1").feature("c2").set("r", 0.25);
    model.component("comp1").geom("geom1").feature("c2").set("pos", new double[]{-0.55, 0.5});
    model.component("comp1").geom("geom1").feature("c2").setIndex("layer", 0.01, 0);
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").set(2, 3, 4, 5);
    model.component("comp1").selection("sel1").label("\u5168\u4fdd\u771f");
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").geom(1);
    model.component("comp1").selection("sel2").set(21, 22, 23, 24);
    model.component("comp1").selection("sel2").label("\u7535\u5c4f\u853d");
    model.component("comp1").selection().create("com1", "Complement");
    model.component("comp1").selection("com1").set("input", new String[]{"sel1"});
    model.component("comp1").selection("com1").label("\u672c\u4f53");

    model.component("comp1").physics("ec").create("gnd1", "Ground", 1);
    model.component("comp1").physics("ec").feature("gnd1").selection().set(10);
    model.component("comp1").physics("ec").create("gnd2", "Ground", 1);
    model.component("comp1").physics("ec").feature("gnd2").selection().set(2);
    model.component("comp1").physics("ec").create("term1", "Terminal", 1);
    model.component("comp1").physics("ec").feature("term1").selection().set(11);
    model.component("comp1").physics("ec").feature("term1").set("TerminalType", "Voltage");
    model.component("comp1").physics("ec").create("term2", "Terminal", 1);
    model.component("comp1").physics("ec").feature("term2").selection().set(3);
    model.component("comp1").physics("ec").feature("term2").set("TerminalType", "Voltage");
    model.component("comp1").physics("ec").create("es1", "ElectricShielding", 1);
    model.component("comp1").physics("ec").feature("es1").selection().named("sel2");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").selection().named("com1");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"sigma_1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermittivity", new String[]{"1"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").selection().named("sel1");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"sigma_2"});
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermittivity", new String[]{"1"});
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").selection().geom("geom1", 1);
    model.component("comp1").material("mat3").selection().named("sel2");
    model.component("comp1").material("mat3").propertyGroup("def").set("relpermittivity", new String[]{"1"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"sigma_2"});

    model.component("comp1").mesh("mesh1").run();

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "sigma_1", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "S/m", 0);
    model.study("std1").feature("param").setIndex("pname", "sigma_1", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "S/m", 0);
    model.study("std1").feature("param").setIndex("pname", "sigma_2", 0);
    model.study("std1").feature("param").setIndex("plistarr", "1e-1 1e1 1e3", 0);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u7535\u52bf (ec)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("solutionparams", "parent");
    model.result("pg1").feature("surf1").set("colortable", "Dipole");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").feature().create("str1", "Streamline");
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("solutionparams", "parent");
    model.result("pg1").feature("str1").set("expr", new String[]{"ec.Ex", "ec.Ey"});
    model.result("pg1").feature("str1").set("titletype", "none");
    model.result("pg1").feature("str1").set("posmethod", "uniform");
    model.result("pg1").feature("str1").set("udist", 0.02);
    model.result("pg1").feature("str1").set("maxlen", 0.4);
    model.result("pg1").feature("str1").set("maxsteps", 5000);
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("inheritcolor", false);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("data", "parent");
    model.result("pg1").feature("str1").selection().geom("geom1", 1);
    model.result("pg1").feature("str1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24);
    model.result("pg1").feature("str1").set("inheritplot", "surf1");
    model.result("pg1").feature("str1").feature().create("col1", "Color");
    model.result("pg1").feature("str1").feature("col1").set("colortable", "DipoleDark");
    model.result("pg1").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg1").feature("str1").feature().create("filt1", "Filter");
    model.result("pg1").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u7535\u573a (ec)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("solutionparams", "parent");
    model.result("pg2").feature("surf1").set("expr", "ec.normE");
    model.result("pg2").feature("surf1").set("colortable", "Prism");
    model.result("pg2").feature("surf1").set("colortabletrans", "nonlinear");
    model.result("pg2").feature("surf1").set("colorcalibration", -0.8);
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg2").feature().create("str1", "Streamline");
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("solutionparams", "parent");
    model.result("pg2").feature("str1").set("expr", new String[]{"ec.Ex", "ec.Ey"});
    model.result("pg2").feature("str1").set("titletype", "none");
    model.result("pg2").feature("str1").set("posmethod", "uniform");
    model.result("pg2").feature("str1").set("udist", 0.02);
    model.result("pg2").feature("str1").set("maxlen", 0.4);
    model.result("pg2").feature("str1").set("maxsteps", 5000);
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("inheritcolor", false);
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("data", "parent");
    model.result("pg2").feature("str1").selection().geom("geom1", 1);
    model.result("pg2").feature("str1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24);
    model.result("pg2").feature("str1").set("inheritplot", "surf1");
    model.result("pg2").feature("str1").feature().create("col1", "Color");
    model.result("pg2").feature("str1").feature("col1").set("expr", "ec.normE");
    model.result("pg2").feature("str1").feature("col1").set("colortable", "PrismDark");
    model.result("pg2").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg2").feature("str1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg2").feature("str1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg2").feature("str1").feature().create("filt1", "Filter");
    model.result("pg2").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg1").run();
    model.result().dataset("dset1").selection().geom("geom1", 2);
    model.result().dataset("dset1").selection().named("com1");
    model.result("pg2").run();
    model.result("pg2").feature("str1").active(false);
    model.result("pg2").run();
    model.result("pg2").create("con1", "Contour");
    model.result("pg2").feature("con1").set("titletype", "none");
    model.result("pg2").feature("con1").set("coloring", "uniform");
    model.result("pg2").feature("con1").set("color", "gray");
    model.result("pg2").feature("con1").set("colorlegend", false);
    model.result("pg2").run();
    model.result("pg2").create("arws1", "ArrowSurface");
    model.result("pg2").feature("arws1").set("expr", new String[]{"ec.Ex", "ec.Ey"});
    model.result("pg2").feature("arws1").set("descr", "\u7535\u573a");
    model.result("pg2").feature("arws1").set("titletype", "none");
    model.result("pg2").feature("arws1").set("xnumber", 21);
    model.result("pg2").feature("arws1").set("ynumber", 14);
    model.result("pg2").feature("arws1").set("color", "gray");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 2, 0);
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 1, 0);
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 3, 0);

    model.title("\u7535\u5c4f\u853d\u6bd4\u8f83");

    model
         .description("\u7535\u5c4f\u853d\u8fb9\u754c\u6761\u4ef6\u8fd1\u4f3c\u6a21\u62df\u4e3a\u8584\u5c42\u7684\u9ad8\u5bfc\u7535\u6750\u6599\uff0c\u5b83\u53ef\u4ee5\u63d0\u4f9b\u4e0e\u8fb9\u754c\u76f8\u5207\u7684\u989d\u5916\u7535\u6d41\u8def\u5f84\u3002\u672c\u4f8b\u5c06\u7535\u5c4f\u853d\u8fb9\u754c\u6761\u4ef6\u4e0e\u9ad8\u4fdd\u771f\u6a21\u578b\u8fdb\u884c\u6bd4\u8f83\uff0c\u5e76\u8ba8\u8bba\u8fd9\u79cd\u8fb9\u754c\u6761\u4ef6\u7684\u9002\u7528\u8303\u56f4\u3002");

    model.label("electric_shielding_comparison.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
