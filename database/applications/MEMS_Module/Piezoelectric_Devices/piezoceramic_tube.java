/*
 * piezoceramic_tube.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:59 by COMSOL 6.3.0.290. */
public class piezoceramic_tube {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\MEMS_Module\\Piezoelectric_Devices");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");
    model.component("comp1").physics("solid").create("pzm1", "PiezoelectricMaterialModel");
    model.component("comp1").physics("solid").feature("pzm1").selection().all();
    model.component("comp1").physics().create("es", "Electrostatics", "geom1");
    model.component("comp1").physics("es").create("ccnp1", "ChargeConservationPiezo");
    model.component("comp1").physics("es").feature("ccnp1").selection().all();

    model.component("comp1").multiphysics().create("pze1", "PiezoelectricEffect", 2);
    model.component("comp1").multiphysics("pze1").set("Solid_physics", "solid");
    model.component("comp1").multiphysics("pze1").set("Electrostatics_physics", "es");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std1").feature("stat").setSolveFor("/physics/es", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/pze1", true);

    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new int[]{240, 620});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new int[]{380, 0});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("StrainCharge", "StrainCharge", "Strain-charge form");
    model.component("comp1").material("mat1").propertyGroup()
         .create("StressCharge", "StressCharge", "Stress-charge form");
    model.component("comp1").material("mat1").label("Lead Zirconate Titanate (PZT-5H)");
    model.component("comp1").material("mat1").set("family", "custom");
    model.component("comp1").material("mat1").set("customspecular", new double[]{0.7843137254901961, 1, 1});
    model.component("comp1").material("mat1").set("diffuse", "custom");
    model.component("comp1").material("mat1")
         .set("customdiffuse", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp1").material("mat1").set("ambient", "custom");
    model.component("comp1").material("mat1")
         .set("customambient", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp1").material("mat1").set("noise", true);
    model.component("comp1").material("mat1").set("fresnel", 0.9);
    model.component("comp1").material("mat1").set("roughness", 0.1);
    model.component("comp1").material("mat1").set("diffusewrap", 0);
    model.component("comp1").material("mat1").set("reflectance", 0);
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
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

    model.component("comp1").physics("solid").feature("pzm1").set("coordinateSystem", "comp1_zx_sys");
    model.component("comp1").physics("solid").create("bndl1", "BoundaryLoad", 1);
    model.component("comp1").physics("solid").feature("bndl1").selection().set(1);
    model.component("comp1").physics("solid").feature("bndl1").set("forceType", "FollowerPressure");
    model.component("comp1").physics("solid").feature("bndl1").set("pressure", "0.1[MPa]");
    model.component("comp1").physics("solid").create("roll1", "Roller", 1);
    model.component("comp1").physics("solid").feature("roll1").selection().set(2);
    model.component("comp1").physics("es").create("gnd1", "Ground", 1);
    model.component("comp1").physics("es").feature("gnd1").selection().set(1, 4);
    model.component("comp1").physics("es").create("pot1", "ElectricPotential", 1);
    model.component("comp1").physics("es").feature("pot1").selection().set(4);
    model.component("comp1").physics("es").feature("pot1").set("V0", 1);

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").set("useadvanceddisable", true);
    model.study("std1").feature("stat").set("disabledphysics", new String[]{"es/pot1"});
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").label("\u5e94\u529b (solid)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg1").feature("surf1").set("threshold", "manual");
    model.result("pg1").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("surf1").set("colortable", "Rainbow");
    model.result("pg1").feature("surf1").set("colortabletrans", "none");
    model.result("pg1").feature("surf1").set("colorscalemode", "linear");
    model.result("pg1").feature("surf1").set("resolution", "normal");
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result("pg1").feature("surf1").create("def", "Deform");
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"u", "w"});
    model.result("pg1").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().dataset().create("dset1solidrev", "Revolve2D");
    model.result().dataset("dset1solidrev").set("data", "dset1");
    model.result().dataset("dset1solidrev").set("revangle", 225);
    model.result().dataset("dset1solidrev").set("startangle", -90);
    model.result().dataset("dset1solidrev").set("hasspacevars", true);
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1solidrev");
    model.result("pg2").label("\u5e94\u529b, 3D (solid)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg2").feature("surf1").set("threshold", "manual");
    model.result("pg2").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg2").feature("surf1").set("colortable", "Rainbow");
    model.result("pg2").feature("surf1").set("colortabletrans", "none");
    model.result("pg2").feature("surf1").set("colorscalemode", "linear");
    model.result("pg2").feature("surf1").set("colortable", "Prism");
    model.result("pg2").feature("surf1").create("def", "Deform");
    model.result().dataset("dset1solidrev").set("hasspacevars", true);
    model.result("pg2").feature("surf1").feature("def").set("revcoordsys", "cylindrical");
    model.result("pg2").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg2").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg2").feature("surf1").feature("def").set("descractive", true);
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u7535\u52bf (es)");
    model.result("pg3").set("dataisaxisym", "off");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").set("showlegendsmaxmin", true);
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("solutionparams", "parent");
    model.result("pg3").feature("surf1").set("expr", "V");
    model.result("pg3").feature("surf1").set("colortable", "Dipole");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result("pg3").feature().create("str1", "Streamline");
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("solutionparams", "parent");
    model.result("pg3").feature("str1").set("expr", new String[]{"es.Er", "es.Ez"});
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
    model.result("pg3").feature("str1").selection().set(1, 2, 3, 4);
    model.result("pg3").feature("str1").set("inheritplot", "surf1");
    model.result("pg3").feature("str1").feature().create("col1", "Color");
    model.result("pg3").feature("str1").feature("col1").set("expr", "V");
    model.result("pg3").feature("str1").feature("col1").set("colortable", "DipoleDark");
    model.result("pg3").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg3").feature("str1").feature().create("filt1", "Filter");
    model.result("pg3").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").set("data", "none");
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev1").set("data", "dset1");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").label("\u7535\u52bf\uff0c\u56de\u8f6c\u51e0\u4f55 (es)");
    model.result("pg4").set("data", "rev1");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").set("showlegendsmaxmin", true);
    model.result("pg4").feature().create("vol1", "Volume");
    model.result("pg4").feature("vol1").set("showsolutionparams", "on");
    model.result("pg4").feature("vol1").set("solutionparams", "parent");
    model.result("pg4").feature("vol1").set("expr", "V");
    model.result("pg4").feature("vol1").set("colortable", "Dipole");
    model.result("pg4").feature("vol1").set("showsolutionparams", "on");
    model.result("pg4").feature("vol1").set("data", "parent");
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").label("\u7535\u573a (es)");
    model.result("pg5").set("dataisaxisym", "off");
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").set("showlegendsmaxmin", true);
    model.result("pg5").feature().create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("solutionparams", "parent");
    model.result("pg5").feature("surf1").set("expr", "es.normE");
    model.result("pg5").feature("surf1").set("colortable", "Prism");
    model.result("pg5").feature("surf1").set("colortabletrans", "nonlinear");
    model.result("pg5").feature("surf1").set("colorcalibration", -0.8);
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("data", "parent");
    model.result("pg5").feature().create("str1", "Streamline");
    model.result("pg5").feature("str1").set("showsolutionparams", "on");
    model.result("pg5").feature("str1").set("solutionparams", "parent");
    model.result("pg5").feature("str1").set("expr", new String[]{"es.Er", "es.Ez"});
    model.result("pg5").feature("str1").set("titletype", "none");
    model.result("pg5").feature("str1").set("posmethod", "uniform");
    model.result("pg5").feature("str1").set("udist", 0.02);
    model.result("pg5").feature("str1").set("maxlen", 0.4);
    model.result("pg5").feature("str1").set("maxsteps", 5000);
    model.result("pg5").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg5").feature("str1").set("inheritcolor", false);
    model.result("pg5").feature("str1").set("showsolutionparams", "on");
    model.result("pg5").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg5").feature("str1").set("showsolutionparams", "on");
    model.result("pg5").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg5").feature("str1").set("showsolutionparams", "on");
    model.result("pg5").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg5").feature("str1").set("showsolutionparams", "on");
    model.result("pg5").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg5").feature("str1").set("data", "parent");
    model.result("pg5").feature("str1").selection().geom("geom1", 1);
    model.result("pg5").feature("str1").selection().set(1, 2, 3, 4);
    model.result("pg5").feature("str1").set("inheritplot", "surf1");
    model.result("pg5").feature("str1").feature().create("col1", "Color");
    model.result("pg5").feature("str1").feature("col1").set("expr", "es.normE");
    model.result("pg5").feature("str1").feature("col1").set("colortable", "PrismDark");
    model.result("pg5").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg5").feature("str1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg5").feature("str1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg5").feature("str1").feature().create("filt1", "Filter");
    model.result("pg5").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg1").run();
    model.result("pg1").label("\u5f84\u5411\u4f4d\u79fb\uff08\u6b63\u6548\u5e94\uff09");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("expr", "u");
    model.result("pg1").feature("surf1").set("descr", "\u4f4d\u79fb\u573a\uff0cR \u5206\u91cf");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").label("\u5e94\u529b\uff0c\u4e09\u7ef4\uff08\u6b63\u6548\u5e94\uff09");
    model.result("pg3").run();
    model.result("pg3").label("\u7535\u52bf\uff08\u6b63\u6548\u5e94\uff09");
    model.result("pg4").run();
    model.result("pg4").label("\u7535\u52bf\uff0c\u4e09\u7ef4\uff08\u6b63\u6548\u5e94\uff09");
    model.result("pg4").run();
    model.result().dataset().create("cln1", "CutLine2D");
    model.result().dataset("cln1").setIndex("genpoints", 380, 0, 0);
    model.result().dataset("cln1").setIndex("genpoints", 300, 0, 1);
    model.result().dataset("cln1").setIndex("genpoints", 620, 1, 0);
    model.result().dataset("cln1").setIndex("genpoints", 300, 1, 1);
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("\u5f84\u5411\u4f4d\u79fb\uff0c\u622a\u7ebf\uff08\u6b63\u6548\u5e94\uff09");
    model.result("pg6").set("data", "cln1");
    model.result("pg6").create("lngr1", "LineGraph");
    model.result("pg6").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg6").feature("lngr1").set("linewidth", "preference");
    model.result("pg6").feature("lngr1").set("expr", "u");
    model.result("pg6").feature("lngr1").set("descr", "\u4f4d\u79fb\u573a\uff0cR \u5206\u91cf");
    model.result("pg6").feature("lngr1").set("unit", "nm");
    model.result("pg6").feature("lngr1").set("xdata", "expr");
    model.result("pg6").feature("lngr1").set("xdataexpr", "r");
    model.result("pg6").run();
    model.result("pg6").run();
    model.result().duplicate("pg7", "pg6");
    model.result("pg7").run();
    model.result("pg7").label("\u7535\u538b\uff0c\u622a\u7ebf\uff08\u6b63\u6548\u5e94\uff09");
    model.result("pg7").run();
    model.result("pg7").feature("lngr1").set("expr", "abs(V)");
    model.result("pg7").run();

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std2").feature("stat").setSolveFor("/physics/es", true);
    model.study("std2").feature("stat").setSolveFor("/multiphysics/pze1", true);
    model.study("std2").feature("stat").set("useadvanceddisable", true);
    model.study("std2").feature("stat").set("disabledphysics", new String[]{"solid/bndl1"});
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg8", "PlotGroup2D");
    model.result("pg8").set("data", "dset2");
    model.result("pg8").label("\u5e94\u529b (solid)");
    model.result("pg8").set("frametype", "spatial");
    model.result("pg8").create("surf1", "Surface");
    model.result("pg8").feature("surf1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg8").feature("surf1").set("threshold", "manual");
    model.result("pg8").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg8").feature("surf1").set("colortable", "Rainbow");
    model.result("pg8").feature("surf1").set("colortabletrans", "none");
    model.result("pg8").feature("surf1").set("colorscalemode", "linear");
    model.result("pg8").feature("surf1").set("resolution", "normal");
    model.result("pg8").feature("surf1").set("colortable", "Prism");
    model.result("pg8").feature("surf1").create("def", "Deform");
    model.result("pg8").feature("surf1").feature("def").set("expr", new String[]{"u", "w"});
    model.result("pg8").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().dataset().create("dset2solidrev", "Revolve2D");
    model.result().dataset("dset2solidrev").set("data", "dset2");
    model.result().dataset("dset2solidrev").set("revangle", 225);
    model.result().dataset("dset2solidrev").set("startangle", -90);
    model.result().dataset("dset2solidrev").set("hasspacevars", true);
    model.result().create("pg9", "PlotGroup3D");
    model.result("pg9").set("data", "dset2solidrev");
    model.result("pg9").label("\u5e94\u529b, 3D (solid)");
    model.result("pg9").set("frametype", "spatial");
    model.result("pg9").create("surf1", "Surface");
    model.result("pg9").feature("surf1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg9").feature("surf1").set("threshold", "manual");
    model.result("pg9").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg9").feature("surf1").set("colortable", "Rainbow");
    model.result("pg9").feature("surf1").set("colortabletrans", "none");
    model.result("pg9").feature("surf1").set("colorscalemode", "linear");
    model.result("pg9").feature("surf1").set("colortable", "Prism");
    model.result("pg9").feature("surf1").create("def", "Deform");
    model.result().dataset("dset2solidrev").set("hasspacevars", true);
    model.result("pg9").feature("surf1").feature("def").set("revcoordsys", "cylindrical");
    model.result("pg9").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg9").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg9").feature("surf1").feature("def").set("descractive", true);
    model.result().create("pg10", "PlotGroup2D");
    model.result("pg10").label("\u7535\u52bf (es)");
    model.result("pg10").set("data", "dset2");
    model.result("pg10").set("dataisaxisym", "off");
    model.result("pg10").set("frametype", "spatial");
    model.result("pg10").set("showlegendsmaxmin", true);
    model.result("pg10").feature().create("surf1", "Surface");
    model.result("pg10").feature("surf1").set("showsolutionparams", "on");
    model.result("pg10").feature("surf1").set("solutionparams", "parent");
    model.result("pg10").feature("surf1").set("expr", "V");
    model.result("pg10").feature("surf1").set("colortable", "Dipole");
    model.result("pg10").feature("surf1").set("showsolutionparams", "on");
    model.result("pg10").feature("surf1").set("data", "parent");
    model.result("pg10").feature().create("str1", "Streamline");
    model.result("pg10").feature("str1").set("showsolutionparams", "on");
    model.result("pg10").feature("str1").set("solutionparams", "parent");
    model.result("pg10").feature("str1").set("expr", new String[]{"es.Er", "es.Ez"});
    model.result("pg10").feature("str1").set("titletype", "none");
    model.result("pg10").feature("str1").set("posmethod", "uniform");
    model.result("pg10").feature("str1").set("udist", 0.02);
    model.result("pg10").feature("str1").set("maxlen", 0.4);
    model.result("pg10").feature("str1").set("maxsteps", 5000);
    model.result("pg10").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg10").feature("str1").set("inheritcolor", false);
    model.result("pg10").feature("str1").set("showsolutionparams", "on");
    model.result("pg10").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg10").feature("str1").set("showsolutionparams", "on");
    model.result("pg10").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg10").feature("str1").set("showsolutionparams", "on");
    model.result("pg10").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg10").feature("str1").set("showsolutionparams", "on");
    model.result("pg10").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg10").feature("str1").set("data", "parent");
    model.result("pg10").feature("str1").selection().geom("geom1", 1);
    model.result("pg10").feature("str1").selection().set(1, 2, 3, 4);
    model.result("pg10").feature("str1").set("inheritplot", "surf1");
    model.result("pg10").feature("str1").feature().create("col1", "Color");
    model.result("pg10").feature("str1").feature("col1").set("expr", "V");
    model.result("pg10").feature("str1").feature("col1").set("colortable", "DipoleDark");
    model.result("pg10").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg10").feature("str1").feature().create("filt1", "Filter");
    model.result("pg10").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().dataset().create("rev2", "Revolve2D");
    model.result().dataset("rev2").set("data", "none");
    model.result().dataset("rev2").set("startangle", -90);
    model.result().dataset("rev2").set("revangle", 225);
    model.result().dataset("rev2").set("data", "dset2");
    model.result().create("pg11", "PlotGroup3D");
    model.result("pg11").label("\u7535\u52bf\uff0c\u56de\u8f6c\u51e0\u4f55 (es)");
    model.result("pg11").set("data", "rev2");
    model.result("pg11").set("frametype", "spatial");
    model.result("pg11").set("showlegendsmaxmin", true);
    model.result("pg11").feature().create("vol1", "Volume");
    model.result("pg11").feature("vol1").set("showsolutionparams", "on");
    model.result("pg11").feature("vol1").set("solutionparams", "parent");
    model.result("pg11").feature("vol1").set("expr", "V");
    model.result("pg11").feature("vol1").set("colortable", "Dipole");
    model.result("pg11").feature("vol1").set("showsolutionparams", "on");
    model.result("pg11").feature("vol1").set("data", "parent");
    model.result().create("pg12", "PlotGroup2D");
    model.result("pg12").label("\u7535\u573a (es) 1");
    model.result("pg12").set("data", "dset2");
    model.result("pg12").set("dataisaxisym", "off");
    model.result("pg12").set("frametype", "spatial");
    model.result("pg12").set("showlegendsmaxmin", true);
    model.result("pg12").feature().create("surf1", "Surface");
    model.result("pg12").feature("surf1").set("showsolutionparams", "on");
    model.result("pg12").feature("surf1").set("solutionparams", "parent");
    model.result("pg12").feature("surf1").set("expr", "es.normE");
    model.result("pg12").feature("surf1").set("colortable", "Prism");
    model.result("pg12").feature("surf1").set("colortabletrans", "nonlinear");
    model.result("pg12").feature("surf1").set("colorcalibration", -0.8);
    model.result("pg12").feature("surf1").set("showsolutionparams", "on");
    model.result("pg12").feature("surf1").set("data", "parent");
    model.result("pg12").feature().create("str1", "Streamline");
    model.result("pg12").feature("str1").set("showsolutionparams", "on");
    model.result("pg12").feature("str1").set("solutionparams", "parent");
    model.result("pg12").feature("str1").set("expr", new String[]{"es.Er", "es.Ez"});
    model.result("pg12").feature("str1").set("titletype", "none");
    model.result("pg12").feature("str1").set("posmethod", "uniform");
    model.result("pg12").feature("str1").set("udist", 0.02);
    model.result("pg12").feature("str1").set("maxlen", 0.4);
    model.result("pg12").feature("str1").set("maxsteps", 5000);
    model.result("pg12").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg12").feature("str1").set("inheritcolor", false);
    model.result("pg12").feature("str1").set("showsolutionparams", "on");
    model.result("pg12").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg12").feature("str1").set("showsolutionparams", "on");
    model.result("pg12").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg12").feature("str1").set("showsolutionparams", "on");
    model.result("pg12").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg12").feature("str1").set("showsolutionparams", "on");
    model.result("pg12").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg12").feature("str1").set("data", "parent");
    model.result("pg12").feature("str1").selection().geom("geom1", 1);
    model.result("pg12").feature("str1").selection().set(1, 2, 3, 4);
    model.result("pg12").feature("str1").set("inheritplot", "surf1");
    model.result("pg12").feature("str1").feature().create("col1", "Color");
    model.result("pg12").feature("str1").feature("col1").set("expr", "es.normE");
    model.result("pg12").feature("str1").feature("col1").set("colortable", "PrismDark");
    model.result("pg12").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg12").feature("str1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg12").feature("str1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg12").feature("str1").feature().create("filt1", "Filter");
    model.result("pg12").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg8").run();
    model.result("pg8").label("\u5f84\u5411\u4f4d\u79fb\uff08\u9006\u6548\u5e94\uff09");
    model.result("pg8").run();
    model.result("pg8").feature("surf1").set("expr", "u");
    model.result("pg8").feature("surf1").set("descr", "\u4f4d\u79fb\u573a\uff0cR \u5206\u91cf");
    model.result("pg8").run();
    model.result("pg9").run();
    model.result("pg9").label("\u5e94\u529b\uff0c\u4e09\u7ef4\uff08\u9006\u6548\u5e94\uff09");
    model.result("pg10").run();
    model.result("pg10").label("\u7535\u52bf\uff08\u9006\u6548\u5e94\uff09");
    model.result("pg11").run();
    model.result("pg11").set("data", "dset2solidrev");
    model.result("pg11").label("\u7535\u52bf\uff0c\u4e09\u7ef4\uff08\u9006\u6548\u5e94\uff09");
    model.result("pg11").run();
    model.result().dataset().duplicate("cln2", "cln1");
    model.result().dataset("cln2").set("data", "dset2");
    model.result("pg6").run();
    model.result().duplicate("pg13", "pg6");
    model.result("pg13").run();
    model.result("pg13").label("\u5f84\u5411\u4f4d\u79fb\uff0c\u622a\u7ebf\uff08\u9006\u6548\u5e94\uff09");
    model.result("pg13").set("data", "cln2");
    model.result("pg13").run();
    model.result("pg7").run();
    model.result().duplicate("pg14", "pg7");
    model.result("pg14").run();
    model.result("pg14").label("\u7535\u538b\uff0c\u622a\u7ebf\uff08\u9006\u6548\u5e94\uff09");
    model.result("pg14").set("data", "cln2");
    model.result("pg14").run();

    model.title("\u538b\u7535\u9676\u74f7\u7ba1");

    return model;
  }

  public static Model run2(Model model) {

    model
         .description("\u672c\u6a21\u578b\u5bf9\u538b\u7535\u6267\u884c\u5668\u6267\u884c\u9759\u6001\u4e8c\u7ef4\u8f74\u5bf9\u79f0\u5206\u6790\uff0c\u5176\u4e2d\u4f7f\u7528\u4e24\u7ec4\u8fb9\u754c\u6761\u4ef6\u6a21\u62df\u5f84\u5411\u6781\u5316\u7684\u538b\u7535\u9676\u74f7\u7ba1\u3002\u7b2c\u4e00\u79cd\u60c5\u51b5\u63cf\u8ff0\u9006\u538b\u7535\u6548\u5e94\uff0c\u7b2c\u4e8c\u79cd\u60c5\u51b5\u6f14\u793a\u6b63\u538b\u7535\u6548\u5e94\u3002\u8be5\u6a21\u578b\u57fa\u4e8e S.M. Peelamedu \u7b49\u4eba\u7684\u8bba\u6587\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("piezoceramic_tube.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
