/*
 * piezoelectric_hysteresis.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:59 by COMSOL 6.3.0.290. */
public class piezoelectric_hysteresis {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\MEMS_Module\\Piezoelectric_Devices");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");
    model.component("comp1").physics().create("es", "Electrostatics", "geom1");
    model.component("comp1").physics("es").create("ccnfe1", "ChargeConservationFerroelectric");
    model.component("comp1").physics("es").feature("ccnfe1").selection().all();

    model.component("comp1").multiphysics().create("efe1", "ElectrostrictiveEffect", 3);
    model.component("comp1").multiphysics("efe1").set("Solid_physics", "solid");
    model.component("comp1").multiphysics("efe1").set("Electrostatics_physics", "es");
    model.component("comp1").multiphysics("efe1").selection().all();

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std1").feature("stat").setSolveFor("/physics/es", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/efe1", true);

    model.param().set("t", "0[s]");
    model.param().descr("t", "\u65f6\u95f4\u53c2\u6570");
    model.param().set("W", "1.5[in]");
    model.param().descr("W", "\u6267\u884c\u5668\u5bbd\u5ea6");
    model.param().set("D", "0.25[in]");
    model.param().descr("D", "\u6267\u884c\u5668\u6df1\u5ea6");
    model.param().set("H", "0.015[in]");
    model.param().descr("H", "\u6267\u884c\u5668\u9ad8\u5ea6");
    model.param().set("alpha", "4.2e6[m/F]");
    model.param().descr("alpha", "\u7574\u95f4\u8026\u5408");
    model.param().set("a", "6.4e5[V/m]");
    model.param().descr("a", "\u7574\u58c1\u5bc6\u5ea6");
    model.param().set("c", "0.2");
    model.param().descr("c", "\u6781\u5316\u53ef\u9006\u6027");
    model.param().set("k", "1e6[V/m]");
    model.param().descr("k", "\u9489\u624e\u635f\u8017");
    model.param().set("Ps", "0.425[C/m^2]");
    model.param().descr("Ps", "\u9971\u548c\u6781\u5316");
    model.param().set("Q11", "3.579e-2[m^4/C^2]");
    model.param().descr("Q11", "\u7535\u81f4\u4f38\u7f29\u8026\u5408\u53c2\u6570");
    model.param().set("Q12", "-5.335e-3[m^4/C^2]");
    model.param().descr("Q12", "\u7535\u81f4\u4f38\u7f29\u8026\u5408\u53c2\u6570");
    model.param().set("Q44", "1.923e-2[m^4/C^2]");
    model.param().descr("Q44", "\u7535\u81f4\u4f38\u7f29\u8026\u5408\u53c2\u6570");
    model.param().set("Vmax", "1200[V]");
    model.param().descr("Vmax", "\u6700\u5927\u5916\u52a0\u7535\u538b");
    model.param().set("F0", "0[MPa]");
    model.param().descr("F0", "\u5916\u52a0\u673a\u68b0\u8f7d\u8377");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1").set("V0", "Vmax*sin(2*pi*t[1/s])");
    model.component("comp1").variable("var1").descr("V0", "\u5916\u52a0\u7535\u538b");

    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"W/2", "D/2", "H/2"});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("solid").feature("lemm1").set("SolidModel", "Anisotropic");
    model.component("comp1").physics("solid").feature("lemm1").set("AnisotropicOption", "AnisotropicVo");
    model.component("comp1").physics("es").feature("ccnfe1").set("Hysteresis", true);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("StrainCharge", "StrainCharge", "Strain-charge form");
    model.component("comp1").material("mat1").propertyGroup()
         .create("StressCharge", "StressCharge", "Stress-charge form");
    model.component("comp1").material("mat1").label("Lead Zirconate Titanate (PZT-5H)");
    model.component("comp1").material("mat1").set("family", "lead");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "440[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"1.3[W/(m*K)]", "0", "0", "0", "1.3[W/(m*K)]", "0", "0", "0", "1.3[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1704.4", "0", "0", "0", "1704.4", "0", "0", "0", "1433.6"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "7500[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("StrainCharge").label("Strain-charge form");
    model.component("comp1").material("mat1").propertyGroup("StrainCharge")
         .set("sE", new String[]{"1.65e-011[1/Pa]", "-4.78e-012[1/Pa]", "-8.45e-012[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "-4.78e-012[1/Pa]", "1.65e-011[1/Pa]", "-8.45e-012[1/Pa]", "0[1/Pa]", 
         "0[1/Pa]", "0[1/Pa]", "-8.45e-012[1/Pa]", "-8.45e-012[1/Pa]", "2.07e-011[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", 
         "0[1/Pa]", "4.35e-011[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "4.35e-011[1/Pa]", "0[1/Pa]", 
         "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "4.26e-011[1/Pa]"});
    model.component("comp1").material("mat1").propertyGroup("StrainCharge")
         .set("dET", new String[]{"0[C/N]", "0[C/N]", "-2.74e-010[C/N]", "0[C/N]", "0[C/N]", "-2.74e-010[C/N]", "0[C/N]", "0[C/N]", "5.93e-010[C/N]", "0[C/N]", 
         "7.41e-010[C/N]", "0[C/N]", "7.41e-010[C/N]", "0[C/N]", "0[C/N]", "0[C/N]", "0[C/N]", "0[C/N]"});
    model.component("comp1").material("mat1").propertyGroup("StrainCharge")
         .set("epsilonrT", new String[]{"3130", "0", "0", "0", "3130", "0", "0", "0", "3400"});
    model.component("comp1").material("mat1").propertyGroup("StressCharge").label("Stress-charge form");
    model.component("comp1").material("mat1").propertyGroup("StressCharge")
         .set("cE", new String[]{"1.27205e+011[Pa]", "8.02122e+010[Pa]", "8.46702e+010[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "8.02122e+010[Pa]", "1.27205e+011[Pa]", "8.46702e+010[Pa]", "0[Pa]", 
         "0[Pa]", "0[Pa]", "8.46702e+010[Pa]", "8.46702e+010[Pa]", "1.17436e+011[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", 
         "0[Pa]", "2.29885e+010[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "2.29885e+010[Pa]", "0[Pa]", 
         "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "2.34742e+010[Pa]"});
    model.component("comp1").material("mat1").propertyGroup("StressCharge")
         .set("eES", new String[]{"0[C/m^2]", "0[C/m^2]", "-6.62281[C/m^2]", "0[C/m^2]", "0[C/m^2]", "-6.62281[C/m^2]", "0[C/m^2]", "0[C/m^2]", "23.2403[C/m^2]", "0[C/m^2]", 
         "17.0345[C/m^2]", "0[C/m^2]", "17.0345[C/m^2]", "0[C/m^2]", "0[C/m^2]", "0[C/m^2]", "0[C/m^2]", "0[C/m^2]"});
    model.component("comp1").material("mat1").propertyGroup("StressCharge")
         .set("epsilonrS", new String[]{"1704.4", "0", "0", "0", "1704.4", "0", "0", "0", "1433.6"});
    model.component("comp1").material("mat1").propertyGroup()
         .create("Ferroelectric", "Ferroelectric", "Ferroelectric");
    model.component("comp1").material("mat1").propertyGroup("Ferroelectric").set("Psat", new String[]{"Ps"});
    model.component("comp1").material("mat1").propertyGroup("Ferroelectric").set("alphaJAe", new String[]{"alpha"});
    model.component("comp1").material("mat1").propertyGroup("Ferroelectric").set("aJAe", new String[]{"a"});
    model.component("comp1").material("mat1").propertyGroup("Ferroelectric").set("kJAe", new String[]{"k"});
    model.component("comp1").material("mat1").propertyGroup("Ferroelectric").set("cJAe", new String[]{"c"});

    model.component("comp1").physics("solid").create("sym1", "SymmetrySolid", 2);
    model.component("comp1").physics("solid").feature("sym1").selection().set(1, 2, 3);
    model.component("comp1").physics("solid").create("bndl1", "BoundaryLoad", 2);
    model.component("comp1").physics("solid").feature("bndl1").selection().set(4);
    model.component("comp1").physics("solid").feature("bndl1")
         .set("forceReferenceArea", new String[]{"0", "0", "F0"});
    model.component("comp1").physics("es").create("gnd1", "Ground", 2);
    model.component("comp1").physics("es").feature("gnd1").selection().set(4);
    model.component("comp1").physics("es").create("pot1", "ElectricPotential", 2);
    model.component("comp1").physics("es").feature("pot1").selection().set(3);
    model.component("comp1").physics("es").feature("pot1").set("V0", "V0/2");

    model.component("comp1").multiphysics("efe1").set("CouplingType", "FullyCoupled");
    model.component("comp1").multiphysics("efe1").set("SolidModel", "CubicCrystal");
    model.component("comp1").multiphysics("efe1").set("Q11", "Q11");
    model.component("comp1").multiphysics("efe1").set("Q12", "Q12");
    model.component("comp1").multiphysics("efe1").set("Q44", "Q44");

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(4);
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 6);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", 2);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "t", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "s", 0);
    model.study("std1").feature("stat").setIndex("pname", "t", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "s", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(0,0.005,3)", 0);
    model.study("std1").create("batsw", "BatchSweep");
    model.study("std1").feature("batsw").setIndex("pname", "t", 0);
    model.study("std1").feature("batsw").setIndex("plistarr", "", 0);
    model.study("std1").feature("batsw").setIndex("punit", "s", 0);
    model.study("std1").feature("batsw").setIndex("pname", "t", 0);
    model.study("std1").feature("batsw").setIndex("plistarr", "", 0);
    model.study("std1").feature("batsw").setIndex("punit", "s", 0);
    model.study("std1").feature("batsw").setIndex("pname", "Vmax", 0);
    model.study("std1").feature("batsw").setIndex("plistarr", "600 1000 1600", 0);
    model.study("std1").feature("batsw").set("clearmesh", false);
    model.study("std1").feature("batsw").set("clearsol", false);
    model.study("std1").feature("batsw").set("maxallow", 3);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("b1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");
    model.batch("b1").feature("daDef").run();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").setIndex("looplevel", 601, 0);
    model.result("pg1").setIndex("looplevel", 3, 1);
    model.result("pg1").label("\u5e94\u529b (solid)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").create("vol1", "Volume");
    model.result("pg1").feature("vol1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg1").feature("vol1").set("threshold", "manual");
    model.result("pg1").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("vol1").set("colortable", "Rainbow");
    model.result("pg1").feature("vol1").set("colortabletrans", "none");
    model.result("pg1").feature("vol1").set("colorscalemode", "linear");
    model.result("pg1").feature("vol1").set("resolution", "custom");
    model.result("pg1").feature("vol1").set("refine", 2);
    model.result("pg1").feature("vol1").set("colortable", "Prism");
    model.result("pg1").feature("vol1").create("def", "Deform");
    model.result("pg1").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg1").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u7535\u52bf (es)");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").setIndex("looplevel", 601, 0);
    model.result("pg2").setIndex("looplevel", 3, 1);
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").feature().create("mslc1", "Multislice");
    model.result("pg2").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg2").feature("mslc1").set("solutionparams", "parent");
    model.result("pg2").feature("mslc1").set("expr", "V");
    model.result("pg2").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg2").feature("mslc1").set("xcoord", "es.CPx");
    model.result("pg2").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg2").feature("mslc1").set("ycoord", "es.CPy");
    model.result("pg2").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg2").feature("mslc1").set("zcoord", "es.CPz");
    model.result("pg2").feature("mslc1").set("colortable", "Dipole");
    model.result("pg2").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg2").feature("mslc1").set("data", "parent");
    model.result("pg2").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg2").feature("strmsl1").set("expr", new String[]{"es.Ex", "es.Ey", "es.Ez"});
    model.result("pg2").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg2").feature("strmsl1").set("xcoord", "es.CPx");
    model.result("pg2").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg2").feature("strmsl1").set("ycoord", "es.CPy");
    model.result("pg2").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg2").feature("strmsl1").set("zcoord", "es.CPz");
    model.result("pg2").feature("strmsl1").set("titletype", "none");
    model.result("pg2").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg2").feature("strmsl1").set("udist", 0.02);
    model.result("pg2").feature("strmsl1").set("maxlen", 0.4);
    model.result("pg2").feature("strmsl1").set("maxsteps", 5000);
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("inheritcolor", false);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("data", "parent");
    model.result("pg2").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg2").feature("strmsl1").feature().create("col1", "Color");
    model.result("pg2").feature("strmsl1").feature("col1").set("expr", "V");
    model.result("pg2").feature("strmsl1").feature("col1").set("colortable", "DipoleDark");
    model.result("pg2").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg2").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg2").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u7535\u573a (es)");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").setIndex("looplevel", 601, 0);
    model.result("pg3").setIndex("looplevel", 3, 1);
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").set("showlegendsmaxmin", true);
    model.result("pg3").feature().create("mslc1", "Multislice");
    model.result("pg3").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg3").feature("mslc1").set("solutionparams", "parent");
    model.result("pg3").feature("mslc1").set("expr", "es.normE");
    model.result("pg3").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg3").feature("mslc1").set("xcoord", "es.CPx");
    model.result("pg3").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg3").feature("mslc1").set("ycoord", "es.CPy");
    model.result("pg3").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg3").feature("mslc1").set("zcoord", "es.CPz");
    model.result("pg3").feature("mslc1").set("colortable", "Prism");
    model.result("pg3").feature("mslc1").set("colortabletrans", "nonlinear");
    model.result("pg3").feature("mslc1").set("colorcalibration", -0.8);
    model.result("pg3").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg3").feature("mslc1").set("data", "parent");
    model.result("pg3").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg3").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg3").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg3").feature("strmsl1").set("expr", new String[]{"es.Ex", "es.Ey", "es.Ez"});
    model.result("pg3").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg3").feature("strmsl1").set("xcoord", "es.CPx");
    model.result("pg3").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg3").feature("strmsl1").set("ycoord", "es.CPy");
    model.result("pg3").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg3").feature("strmsl1").set("zcoord", "es.CPz");
    model.result("pg3").feature("strmsl1").set("titletype", "none");
    model.result("pg3").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg3").feature("strmsl1").set("udist", 0.02);
    model.result("pg3").feature("strmsl1").set("maxlen", 0.4);
    model.result("pg3").feature("strmsl1").set("maxsteps", 5000);
    model.result("pg3").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("strmsl1").set("inheritcolor", false);
    model.result("pg3").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg3").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg3").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg3").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg3").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("strmsl1").set("data", "parent");
    model.result("pg3").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg3").feature("strmsl1").feature().create("col1", "Color");
    model.result("pg3").feature("strmsl1").feature("col1").set("expr", "es.normE");
    model.result("pg3").feature("strmsl1").feature("col1").set("colortable", "PrismDark");
    model.result("pg3").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg3").feature("strmsl1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg3").feature("strmsl1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg3").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg3").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
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
    model.result("pg4").feature("ptgr1").set("autopoint", false);
    model.result("pg4").feature("ptgr1").set("legend", true);
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().duplicate("pg5", "pg4");
    model.result("pg5").run();
    model.result("pg5").label("\u7535\u81f4\u4f38\u7f29");
    model.result("pg5").set("yspacing", 0.001);
    model.result("pg5").run();
    model.result("pg5").feature("ptgr1").set("expr", "efe1.emZZ");
    model.result("pg5").run();

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std2").feature("stat").setSolveFor("/physics/es", true);
    model.study("std2").feature("stat").setSolveFor("/multiphysics/efe1", true);
    model.study("std2").feature("stat").set("useparam", true);
    model.study("std2").feature("stat").setIndex("pname", "t", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "s", 0);
    model.study("std2").feature("stat").setIndex("pname", "t", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "s", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "range(0,0.005,3)", 0);
    model.study("std2").create("batsw", "BatchSweep");
    model.study("std2").feature("batsw").setIndex("pname", "t", 0);
    model.study("std2").feature("batsw").setIndex("plistarr", "", 0);
    model.study("std2").feature("batsw").setIndex("punit", "s", 0);
    model.study("std2").feature("batsw").setIndex("pname", "t", 0);
    model.study("std2").feature("batsw").setIndex("plistarr", "", 0);
    model.study("std2").feature("batsw").setIndex("punit", "s", 0);
    model.study("std2").feature("batsw").setIndex("pname", "F0", 0);
    model.study("std2").feature("batsw").setIndex("plistarr", "0 -25 -50", 0);
    model.study("std2").feature("batsw").setIndex("punit", "MPa", 0);
    model.study("std2").feature("batsw").set("clearmesh", false);
    model.study("std2").feature("batsw").set("clearsol", false);
    model.study("std2").feature("batsw").set("maxallow", 3);
    model.study("std2").createAutoSequences("all");

    model.sol().create("sol7");
    model.sol("sol7").study("std2");
    model.sol("sol7").label("\u53c2\u6570\u5316\u89e3 2");

    model.batch("b2").feature("so1").set("psol", "sol7");
    model.batch("p2").run("compute");
    model.batch("b2").feature("daDef").run();

    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").set("data", "dset4");
    model.result("pg6").setIndex("looplevel", 601, 0);
    model.result("pg6").setIndex("looplevel", 3, 1);
    model.result("pg6").label("\u5e94\u529b (solid) 1");
    model.result("pg6").set("frametype", "spatial");
    model.result("pg6").create("vol1", "Volume");
    model.result("pg6").feature("vol1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg6").feature("vol1").set("threshold", "manual");
    model.result("pg6").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg6").feature("vol1").set("colortable", "Rainbow");
    model.result("pg6").feature("vol1").set("colortabletrans", "none");
    model.result("pg6").feature("vol1").set("colorscalemode", "linear");
    model.result("pg6").feature("vol1").set("resolution", "custom");
    model.result("pg6").feature("vol1").set("refine", 2);
    model.result("pg6").feature("vol1").set("colortable", "Prism");
    model.result("pg6").feature("vol1").create("def", "Deform");
    model.result("pg6").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg6").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").label("\u7535\u52bf (es) 1");
    model.result("pg7").set("data", "dset4");
    model.result("pg7").setIndex("looplevel", 601, 0);
    model.result("pg7").setIndex("looplevel", 3, 1);
    model.result("pg7").set("frametype", "spatial");
    model.result("pg7").set("showlegendsmaxmin", true);
    model.result("pg7").feature().create("mslc1", "Multislice");
    model.result("pg7").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg7").feature("mslc1").set("solutionparams", "parent");
    model.result("pg7").feature("mslc1").set("expr", "V");
    model.result("pg7").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg7").feature("mslc1").set("xcoord", "es.CPx");
    model.result("pg7").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg7").feature("mslc1").set("ycoord", "es.CPy");
    model.result("pg7").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg7").feature("mslc1").set("zcoord", "es.CPz");
    model.result("pg7").feature("mslc1").set("colortable", "Dipole");
    model.result("pg7").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg7").feature("mslc1").set("data", "parent");
    model.result("pg7").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg7").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg7").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg7").feature("strmsl1").set("expr", new String[]{"es.Ex", "es.Ey", "es.Ez"});
    model.result("pg7").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg7").feature("strmsl1").set("xcoord", "es.CPx");
    model.result("pg7").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg7").feature("strmsl1").set("ycoord", "es.CPy");
    model.result("pg7").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg7").feature("strmsl1").set("zcoord", "es.CPz");
    model.result("pg7").feature("strmsl1").set("titletype", "none");
    model.result("pg7").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg7").feature("strmsl1").set("udist", 0.02);
    model.result("pg7").feature("strmsl1").set("maxlen", 0.4);
    model.result("pg7").feature("strmsl1").set("maxsteps", 5000);
    model.result("pg7").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg7").feature("strmsl1").set("inheritcolor", false);
    model.result("pg7").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg7").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg7").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg7").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg7").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg7").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg7").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg7").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg7").feature("strmsl1").set("data", "parent");
    model.result("pg7").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg7").feature("strmsl1").feature().create("col1", "Color");
    model.result("pg7").feature("strmsl1").feature("col1").set("expr", "V");
    model.result("pg7").feature("strmsl1").feature("col1").set("colortable", "DipoleDark");
    model.result("pg7").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg7").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg7").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").label("\u7535\u573a (es) 1");
    model.result("pg8").set("data", "dset4");
    model.result("pg8").setIndex("looplevel", 601, 0);
    model.result("pg8").setIndex("looplevel", 3, 1);
    model.result("pg8").set("frametype", "spatial");
    model.result("pg8").set("showlegendsmaxmin", true);
    model.result("pg8").feature().create("mslc1", "Multislice");
    model.result("pg8").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg8").feature("mslc1").set("solutionparams", "parent");
    model.result("pg8").feature("mslc1").set("expr", "es.normE");
    model.result("pg8").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg8").feature("mslc1").set("xcoord", "es.CPx");
    model.result("pg8").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg8").feature("mslc1").set("ycoord", "es.CPy");
    model.result("pg8").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg8").feature("mslc1").set("zcoord", "es.CPz");
    model.result("pg8").feature("mslc1").set("colortable", "Prism");
    model.result("pg8").feature("mslc1").set("colortabletrans", "nonlinear");
    model.result("pg8").feature("mslc1").set("colorcalibration", -0.8);
    model.result("pg8").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg8").feature("mslc1").set("data", "parent");
    model.result("pg8").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg8").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg8").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg8").feature("strmsl1").set("expr", new String[]{"es.Ex", "es.Ey", "es.Ez"});
    model.result("pg8").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg8").feature("strmsl1").set("xcoord", "es.CPx");
    model.result("pg8").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg8").feature("strmsl1").set("ycoord", "es.CPy");
    model.result("pg8").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg8").feature("strmsl1").set("zcoord", "es.CPz");
    model.result("pg8").feature("strmsl1").set("titletype", "none");
    model.result("pg8").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg8").feature("strmsl1").set("udist", 0.02);

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg8").feature("strmsl1").set("maxlen", 0.4);
    model.result("pg8").feature("strmsl1").set("maxsteps", 5000);
    model.result("pg8").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg8").feature("strmsl1").set("inheritcolor", false);
    model.result("pg8").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg8").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg8").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg8").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg8").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg8").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg8").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg8").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg8").feature("strmsl1").set("data", "parent");
    model.result("pg8").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg8").feature("strmsl1").feature().create("col1", "Color");
    model.result("pg8").feature("strmsl1").feature("col1").set("expr", "es.normE");
    model.result("pg8").feature("strmsl1").feature("col1").set("colortable", "PrismDark");
    model.result("pg8").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg8").feature("strmsl1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg8").feature("strmsl1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg8").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg8").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg6").run();
    model.result("pg6").feature("vol1").set("unit", "MPa");
    model.result("pg6").run();
    model.result("pg4").run();
    model.result().duplicate("pg9", "pg4");
    model.result("pg9").run();
    model.result("pg9").set("data", "dset4");
    model.result("pg9").run();
    model.result("pg5").run();
    model.result().duplicate("pg10", "pg5");
    model.result("pg10").run();
    model.result("pg10").label("\u5e94\u53d8");
    model.result("pg10").set("data", "dset4");
    model.result("pg10").run();
    model.result("pg10").feature("ptgr1").set("expr", "solid.eZZ");
    model.result("pg10").run();

    model.batch("b1").feature("daDef").set("operation", "clearalldata");
    model.batch("b1").feature("daDef").run();
    model.batch("b2").feature("daDef").set("operation", "clearalldata");
    model.batch("b2").feature("daDef").run();

    model.result("pg10").run();

    model.title("\u538b\u7535\u9676\u74f7\u7684\u6ede\u56de\u73b0\u8c61");

    model
         .description("\u8bb8\u591a\u538b\u7535\u6750\u6599\u90fd\u5177\u6709\u94c1\u7535\u6027\u3002\u94c1\u7535\u6750\u6599\u5728\u8f83\u5927\u7684\u5916\u52a0\u7535\u573a\u4f5c\u7528\u4e0b\u4f1a\u8868\u73b0\u51fa\u975e\u7ebf\u6027\u6781\u5316\u7279\u6027\uff0c\u4f8b\u5982\u6ede\u56de\u548c\u9971\u548c\u3002\u6b64\u5916\uff0c\u7531\u4e8e\u7535\u81f4\u4f38\u7f29\u6548\u5e94\uff0c\u8fd9\u79cd\u6750\u6599\u7684\u6781\u5316\u548c\u673a\u68b0\u53d8\u5f62\u53ef\u4ee5\u5b9e\u73b0\u5f3a\u8026\u5408\u3002\u8be5\u6a21\u578b\u4f7f\u7528\u201c\u94c1\u7535\u5f39\u6027\u201d\u63a5\u53e3\u5206\u6790\u4e00\u4e2a\u7b80\u5355\u7684\u7531 PZT \u538b\u7535\u9676\u74f7\u6750\u6599\u5236\u6210\u7684\u6267\u884c\u5668\u5728\u5916\u52a0\u7535\u573a\u548c\u673a\u68b0\u8f7d\u8377\u4f5c\u7528\u4e0b\u7684\u6027\u80fd\u8868\u73b0\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();
    model.sol("sol8").clearSolutionData();
    model.sol("sol9").clearSolutionData();
    model.sol("sol10").clearSolutionData();

    model.label("piezoelectric_hysteresis.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
