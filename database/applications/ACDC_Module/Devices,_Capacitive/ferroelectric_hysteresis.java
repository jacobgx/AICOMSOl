/*
 * ferroelectric_hysteresis.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:14 by COMSOL 6.3.0.290. */
public class ferroelectric_hysteresis {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Devices,_Capacitive");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("es", "Electrostatics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/es", true);

    model.param().set("t", "0[s]");
    model.param().descr("t", "\u65f6\u95f4\u53c2\u6570");
    model.param().set("H0", "0.01[in]");
    model.param().descr("H0", "\u6267\u884c\u5668\u539a\u5ea6");
    model.param().set("R0", "0.5[in]");
    model.param().descr("R0", "\u6267\u884c\u5668\u534a\u5f84");
    model.param().set("Vmax", "1600[V]");
    model.param().descr("Vmax", "\u6700\u5927\u5916\u52a0\u7535\u538b");
    model.param().set("alpha", "3.6e6[m/F]");
    model.param().descr("alpha", "\u7574\u95f4\u8026\u5408");
    model.param().set("a", "4.4e5[V/m]");
    model.param().descr("a", "\u7574\u58c1\u5bc6\u5ea6");
    model.param().set("c", "0.18");
    model.param().descr("c", "\u6781\u5316\u53ef\u9006\u6027");
    model.param().set("k", "1.9e6[V/m]");
    model.param().descr("k", "\u9489\u624e\u635f\u8017");
    model.param().set("Ps", "0.49[C/m^2]");
    model.param().descr("Ps", "\u9971\u548c\u6781\u5316");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1").set("V0", "Vmax*sin(2*pi*t[1/s])");
    model.component("comp1").variable("var1").descr("V0", "\u5916\u52a0\u7535\u538b");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"R0", "H0/2"});
    model.component("comp1").geom("geom1").feature().duplicate("r2", "r1");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"1.5*R0", "5*H0"});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("es").create("ccns1", "ChargeConservationSolid", 2);
    model.component("comp1").physics("es").feature("ccns1").label("\u7535\u8377\u5b88\u6052\uff1aPZT5A");
    model.component("comp1").physics("es").feature("ccns1").selection().set(1);
    model.component("comp1").physics("es").feature("ccns1").set("ConstitutiveRelationDE", "Ferroelectric");
    model.component("comp1").physics("es").feature("ccns1").set("Hysteresis", true);
    model.component("comp1").physics("es").create("gnd1", "Ground", 1);
    model.component("comp1").physics("es").feature("gnd1").selection().set(4);
    model.component("comp1").physics("es").create("pot1", "ElectricPotential", 1);
    model.component("comp1").physics("es").feature("pot1").selection().set(2);
    model.component("comp1").physics("es").feature("pot1").set("V0", "V0/2");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("PZT5A");
    model.component("comp1").material("mat1").selection().set(1);
    model.component("comp1").material("mat1").propertyGroup()
         .create("Ferroelectric", "Ferroelectric", "Ferroelectric");
    model.component("comp1").material("mat1").propertyGroup("Ferroelectric").set("Psat", new String[]{"Ps"});
    model.component("comp1").material("mat1").propertyGroup("Ferroelectric").set("alphaJAe", new String[]{"alpha"});
    model.component("comp1").material("mat1").propertyGroup("Ferroelectric").set("aJAe", new String[]{"a"});
    model.component("comp1").material("mat1").propertyGroup("Ferroelectric").set("kJAe", new String[]{"k"});
    model.component("comp1").material("mat1").propertyGroup("Ferroelectric").set("cJAe", new String[]{"c"});

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 1);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(1, 6);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(2, 4);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("elemcount", 12);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("elemratio", 24);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("growthrate", "exponential");
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "t", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "s", 0);
    model.study("std1").feature("stat").setIndex("pname", "t", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "s", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(0,0.005,4)", 0);
    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "t", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "s", 0);
    model.study("std1").feature("param").setIndex("pname", "t", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "s", 0);
    model.study("std1").feature("param").setIndex("pname", "Vmax", 0);
    model.study("std1").feature("param").setIndex("plistarr", "600 800 1000 1600", 0);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u7535\u52bf (es)");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").setIndex("looplevel", 801, 0);
    model.result("pg1").setIndex("looplevel", 4, 1);
    model.result("pg1").set("dataisaxisym", "off");
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
    model.result("pg1").feature("str1").selection().set(1, 2, 3, 4, 5, 6, 7, 8);
    model.result("pg1").feature("str1").set("inheritplot", "surf1");
    model.result("pg1").feature("str1").feature().create("col1", "Color");
    model.result("pg1").feature("str1").feature("col1").set("colortable", "DipoleDark");
    model.result("pg1").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg1").feature("str1").feature().create("filt1", "Filter");
    model.result("pg1").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").set("data", "none");
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev1").set("data", "dset2");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u7535\u52bf\uff0c\u56de\u8f6c\u51e0\u4f55 (es)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").feature().create("vol1", "Volume");
    model.result("pg2").feature("vol1").set("showsolutionparams", "on");
    model.result("pg2").feature("vol1").set("solutionparams", "parent");
    model.result("pg2").feature("vol1").set("colortable", "Dipole");
    model.result("pg2").feature("vol1").set("showsolutionparams", "on");
    model.result("pg2").feature("vol1").set("data", "parent");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u7535\u573a (es)");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").setIndex("looplevel", 801, 0);
    model.result("pg3").setIndex("looplevel", 4, 1);
    model.result("pg3").set("dataisaxisym", "off");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").set("showlegendsmaxmin", true);
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("solutionparams", "parent");
    model.result("pg3").feature("surf1").set("expr", "es.normE");
    model.result("pg3").feature("surf1").set("colortable", "Prism");
    model.result("pg3").feature("surf1").set("colortabletrans", "nonlinear");
    model.result("pg3").feature("surf1").set("colorcalibration", -0.8);
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result("pg3").feature().create("str1", "Streamline");
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("solutionparams", "parent");
    model.result("pg3").feature("str1").set("titletype", "none");
    model.result("pg3").feature("str1").set("posmethod", "uniform");
    model.result("pg3").feature("str1").set("udist", 0.02);
    model.result("pg3").feature("str1").set("maxlen", 0.4);
    model.result("pg3").feature("str1").set("maxsteps", 5000);
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("inheritcolor", false);
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("data", "parent");
    model.result("pg3").feature("str1").selection().geom("geom1", 1);
    model.result("pg3").feature("str1").selection().set(1, 2, 3, 4, 5, 6, 7, 8);
    model.result("pg3").feature("str1").set("inheritplot", "surf1");
    model.result("pg3").feature("str1").feature().create("col1", "Color");
    model.result("pg3").feature("str1").feature("col1").set("expr", "es.normE");
    model.result("pg3").feature("str1").feature("col1").set("colortable", "PrismDark");
    model.result("pg3").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg3").feature("str1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg3").feature("str1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg3").feature("str1").feature().create("filt1", "Filter");
    model.result("pg3").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 51, 0);
    model.result("pg1").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u6781\u5316");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").set("manualgrid", true);
    model.result("pg4").set("yspacing", 0.1);
    model.result("pg4").set("legendpos", "lowerright");
    model.result("pg4").create("ptgr1", "PointGraph");
    model.result("pg4").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg4").feature("ptgr1").set("linewidth", "preference");
    model.result("pg4").feature("ptgr1").selection().set(1);
    model.result("pg4").feature("ptgr1").set("expr", "es.PZ");
    model.result("pg4").feature("ptgr1").set("xdata", "expr");
    model.result("pg4").feature("ptgr1").set("xdataexpr", "es.EZ");
    model.result("pg4").feature("ptgr1").set("xdataunit", "MV/m");
    model.result("pg4").feature("ptgr1").set("legend", true);
    model.result("pg4").feature("ptgr1").set("autopoint", false);
    model.result("pg4").run();
    model.result("pg4").run();

    model.title("\u94c1\u7535\u6750\u6599\u7684\u6ede\u56de\u73b0\u8c61");

    model
         .description("\u94c1\u7535\u6750\u6599\u5728\u8f83\u5927\u7684\u5916\u52a0\u7535\u573a\u4f5c\u7528\u4e0b\u4f1a\u8868\u73b0\u51fa\u975e\u7ebf\u6027\u6781\u5316\u7279\u6027\uff0c\u4f8b\u5982\u6ede\u56de\u548c\u9971\u548c\u3002\u8bb8\u591a\u538b\u7535\u6750\u6599\u90fd\u5177\u6709\u94c1\u7535\u6027\u3002\u8be5\u6a21\u578b\u5206\u6790\u4e00\u4e2a\u7b80\u5355\u7684\u7531 PZT \u538b\u7535\u9676\u74f7\u6750\u6599\u5236\u6210\u7684\u6267\u884c\u5668\u5728\u5916\u52a0\u7535\u573a\u4f5c\u7528\u4e0b\u7684\u6027\u80fd\u8868\u73b0\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();

    model.label("ferroelectric_hysteresis.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
