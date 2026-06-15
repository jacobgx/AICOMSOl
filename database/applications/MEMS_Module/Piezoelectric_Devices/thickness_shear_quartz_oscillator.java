/*
 * thickness_shear_quartz_oscillator.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:01 by COMSOL 6.3.0.290. */
public class thickness_shear_quartz_oscillator {

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
    model.component("comp1").physics("solid").create("pzm1", "PiezoelectricMaterialModel");
    model.component("comp1").physics("solid").feature("pzm1").selection().all();
    model.component("comp1").physics().create("es", "Electrostatics", "geom1");
    model.component("comp1").physics("es").create("ccnp1", "ChargeConservationPiezo");
    model.component("comp1").physics("es").feature("ccnp1").selection().all();

    model.component("comp1").multiphysics().create("pze1", "PiezoelectricEffect", 3);
    model.component("comp1").multiphysics("pze1").set("Solid_physics", "solid");
    model.component("comp1").multiphysics("pze1").set("Electrostatics_physics", "es");

    model.param().set("Cs", "1[pF]");
    model.param().descr("Cs", "\u4e32\u8054\u7535\u5bb9");
    model.param().set("R0", "835[um]");
    model.param().descr("R0", "\u632f\u8361\u5668\u534a\u5f84");
    model.param().set("H0", "334[um]");
    model.param().descr("H0", "\u632f\u8361\u5668\u539a\u5ea6");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").mesh("mesh1").create("imp1", "Import");
    model.component("comp1").mesh("mesh1").feature("imp1")
         .set("filename", "thickness_shear_quartz_oscillator_mesh.mphbin");
    model.component("comp1").mesh("mesh1").feature("imp1").importData();

    model.component("comp1").cpl().create("intop1", "Integration");

    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 0);
    model.component("comp1").cpl("intop1").selection().set(8);

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u6240\u6709\u8868\u9762");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").all();

    model.component("comp1").coordSystem().create("sys2", "Rotated");
    model.component("comp1").coordSystem("sys2").set("angle", new String[]{"0", "125.25[deg]", "0"});

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("StrainCharge", "StrainCharge", "Strain-charge form");
    model.component("comp1").material("mat1").propertyGroup()
         .create("StressCharge", "StressCharge", "Stress-charge form");
    model.component("comp1").material("mat1").label("Quartz LH (1978 IEEE)");
    model.component("comp1").material("mat1").set("family", "custom");
    model.component("comp1").material("mat1").set("diffuse", "custom");
    model.component("comp1").material("mat1").set("ambient", "custom");
    model.component("comp1").material("mat1").set("noise", true);
    model.component("comp1").material("mat1").set("fresnel", 0.99);
    model.component("comp1").material("mat1").set("roughness", 0.02);
    model.component("comp1").material("mat1").set("diffusewrap", 0);
    model.component("comp1").material("mat1").set("reflectance", 0);
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"4.428", "0", "0", "0", "4.428", "0", "0", "0", "4.634"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "2651[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("StrainCharge").label("Strain-charge form");
    model.component("comp1").material("mat1").propertyGroup("StrainCharge")
         .set("sE", new String[]{"1.277e-011[1/Pa]", "-1.79e-012[1/Pa]", "-1.22e-012[1/Pa]", "-4.5e-012[1/Pa]", "0[1/Pa]", "0[1/Pa]", "-1.79e-012[1/Pa]", "1.277e-011[1/Pa]", "-1.22e-012[1/Pa]", "4.5e-012[1/Pa]", 
         "0[1/Pa]", "0[1/Pa]", "-1.22e-012[1/Pa]", "-1.22e-012[1/Pa]", "9.6e-012[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "-4.5e-012[1/Pa]", "4.5e-012[1/Pa]", 
         "0[1/Pa]", "2.00e-011[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "2.00e-011[1/Pa]", "-9e-012[1/Pa]", 
         "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "-9e-012[1/Pa]", "2.912e-011[1/Pa]"});
    model.component("comp1").material("mat1").propertyGroup("StrainCharge")
         .set("dET", new String[]{"-2.307e-012[C/N]", "0[C/N]", "0[C/N]", "2.307e-012[C/N]", "0[C/N]", "0[C/N]", "0[C/N]", "0[C/N]", "0[C/N]", "7.25e-013[C/N]", 
         "0[C/N]", "0[C/N]", "0[C/N]", "-7.25e-013[C/N]", "0[C/N]", "0[C/N]", "4.6e-012[C/N]", "0[C/N]"});
    model.component("comp1").material("mat1").propertyGroup("StrainCharge")
         .set("epsilonrT", new String[]{"4.514", "0", "0", "0", "4.514", "0", "0", "0", "4.634"});
    model.component("comp1").material("mat1").propertyGroup("StressCharge").label("Stress-charge form");
    model.component("comp1").material("mat1").propertyGroup("StressCharge")
         .set("cE", new String[]{"8.67362e+010[Pa]", "6.98527e+009[Pa]", "1.19104e+010[Pa]", "1.79081e+010[Pa]", "0[Pa]", "0[Pa]", "6.98527e+009[Pa]", "8.67362e+010[Pa]", "1.19104e+010[Pa]", "-1.79081e+010[Pa]", 
         "0[Pa]", "0[Pa]", "1.19104e+010[Pa]", "1.19104e+010[Pa]", "1.07194e+011[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "1.79081e+010[Pa]", "-1.79081e+010[Pa]", 
         "0[Pa]", "5.79428e+010[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "5.79492e+010[Pa]", "1.79224e+010[Pa]", 
         "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "1.79224e+010[Pa]", "3.99073e+010[Pa]"});
    model.component("comp1").material("mat1").propertyGroup("StressCharge")
         .set("eES", new String[]{"-0.1710[C/m^2]", "0[C/m^2]", "0[C/m^2]", "0.1710[C/m^2]", "0[C/m^2]", "0[C/m^2]", "0[C/m^2]", "0[C/m^2]", "0[C/m^2]", "-0.0406[C/m^2]", 
         "0[C/m^2]", "0[C/m^2]", "0[C/m^2]", "0.0406[C/m^2]", "0[C/m^2]", "0[C/m^2]", "0.1710[C/m^2]", "0[C/m^2]"});
    model.component("comp1").material("mat1").propertyGroup("StressCharge")
         .set("epsilonrS", new String[]{"4.428", "0", "0", "0", "4.428", "0", "0", "0", "4.634"});

    model.component("comp1").physics("solid").feature("pzm1").set("coordinateSystem", "sys2");
    model.component("comp1").physics("solid").feature("pzm1").create("mdmp1", "MechanicalDamping", 3);
    model.component("comp1").physics("solid").feature("pzm1").feature("mdmp1")
         .set("DampingType", "IsotropicLossFactor");
    model.component("comp1").physics("solid").feature("pzm1").feature("mdmp1").set("eta_s_mat", "userdef");
    model.component("comp1").physics("solid").feature("pzm1").feature("mdmp1").set("eta_s", "1e-3");
    model.component("comp1").physics("es").create("term1", "Terminal", 2);
    model.component("comp1").physics("es").feature("term1").selection().set(4);
    model.component("comp1").physics("es").feature("term1").set("TerminalType", "Circuit");
    model.component("comp1").physics("es").create("term2", "Terminal", 2);
    model.component("comp1").physics("es").feature("term2").set("TerminalName", "1");
    model.component("comp1").physics("es").feature("term2").selection().set(4);
    model.component("comp1").physics("es").feature("term2").set("TerminalType", "Voltage");
    model.component("comp1").physics("es").feature("term2").set("V0", 10);
    model.component("comp1").physics("es").create("gnd1", "Ground", 2);
    model.component("comp1").physics("es").feature("gnd1").selection().set(3);
    model.component("comp1").physics().create("cir", "Circuit", "geom1");
    model.component("comp1").physics("cir").create("V1", "VoltageSource", -1);
    model.component("comp1").physics("cir").feature("V1").setIndex("Connections", 2, 0, 0);
    model.component("comp1").physics("cir").feature("V1").setIndex("Connections", 0, 1, 0);
    model.component("comp1").physics("cir").feature("V1").set("sourceType", "AC");
    model.component("comp1").physics("cir").feature("V1").set("value", 10);
    model.component("comp1").physics("cir").create("C1", "Capacitor", -1);
    model.component("comp1").physics("cir").feature("C1").setIndex("Connections", 2, 0, 0);
    model.component("comp1").physics("cir").feature("C1").setIndex("Connections", 1, 1, 0);
    model.component("comp1").physics("cir").feature("C1").set("C", "Cs");
    model.component("comp1").physics("cir").create("termI1", "ModelTerminalIV", -1);
    model.component("comp1").physics("cir").feature("termI1").set("Connections", 1);
    model.component("comp1").physics("cir").feature("termI1").set("V_src", "root.comp1.es.V0_1");

    model.study().create("std1");
    model.study("std1").create("frawe", "FrequencyAdaptive");
    model.study("std1").feature("frawe").set("plist", "range(5.095[MHz],0.2[kHz],5.13[MHz])");
    model.study("std1").feature("frawe").set("awefunctype", "usercontrolled");
    model.study("std1").feature("frawe").setIndex("awefunc", "comp1.intop1(abs(comp1.u)/1e-9)", 0);
    model.study("std1").feature("frawe").setSolveFor("/physics/cir", false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 176, 0);
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
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").label("\u4f4d\u79fb");
    model.result("pg1").setIndex("looplevel", 86, 0);
    model.result("pg1").run();
    model.result("pg1").feature("vol1").set("expr", "solid.disp");
    model.result("pg1").feature("vol1").set("descr", "\u4f4d\u79fb\u5927\u5c0f");
    model.result("pg1").feature("vol1").set("unit", "nm");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 86, 0);
    model.result("pg2").run();
    model.result("pg2").feature("mslc1").set("multiplanexmethod", "number");
    model.result("pg2").feature("mslc1").set("xnumber", "5");
    model.result("pg2").feature("mslc1").set("multiplaneymethod", "number");
    model.result("pg2").feature("mslc1").set("ynumber", "0");
    model.result("pg2").feature("mslc1").set("multiplanezmethod", "number");
    model.result("pg2").feature("mslc1").set("znumber", "0");
    model.result("pg2").run();
    model.result("pg2").feature("strmsl1").set("multiplanexmethod", "number");
    model.result("pg2").feature("strmsl1").set("xnumber", "5");
    model.result("pg2").feature("strmsl1").set("multiplaneymethod", "number");
    model.result("pg2").feature("strmsl1").set("ynumber", "0");
    model.result("pg2").feature("strmsl1").set("multiplanezmethod", "number");
    model.result("pg2").feature("strmsl1").set("znumber", "0");
    model.result("pg2").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u529b\u5b66\u54cd\u5e94");
    model.result("pg4").create("ptgr1", "PointGraph");
    model.result("pg4").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg4").feature("ptgr1").set("linewidth", "preference");
    model.result("pg4").feature("ptgr1").selection().set(8);
    model.result("pg4").feature("ptgr1").set("expr", "abs(u)");
    model.result("pg4").feature("ptgr1").set("unit", "nm");
    model.result("pg4").feature("ptgr1").set("xdataparamunit", "MHz");
    model.result("pg4").run();

    model.study().create("std2");
    model.study("std2").create("frawe", "FrequencyAdaptive");
    model.study("std2").feature("frawe").set("plist", "range(5.095[MHz],0.2[kHz],5.13[MHz])");
    model.study("std2").feature("frawe").set("awefunctype", "usercontrolled");
    model.study("std2").feature("frawe").setIndex("awefunc", "comp1.intop1(abs(comp1.u)/1e-9)", 0);
    model.study("std2").feature("frawe").set("useadvanceddisable", true);
    model.study("std2").feature("frawe").set("disabledphysics", new String[]{"es/term2"});
    model.study("std2").feature("frawe").setEntry("outputmap", "solid", "selection");
    model.study("std2").feature("frawe").setEntry("outputselectionmap", "solid", "sel1");
    model.study("std2").feature("frawe").setEntry("outputmap", "es", "selection");
    model.study("std2").feature("frawe").setEntry("outputselectionmap", "es", "sel1");
    model.study("std2").create("param", "Parametric");
    model.study("std2").feature("param").setIndex("pname", "Cs", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "F", 0);
    model.study("std2").feature("param").setIndex("pname", "Cs", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "F", 0);
    model.study("std2").feature("param").setIndex("plistarr", "0.1 0.4 1", 0);
    model.study("std2").feature("param").setIndex("punit", "pF", 0);
    model.study("std2").setGenPlots(false);
    model.study("std2").createAutoSequences("all");

    model.sol().create("sol3");
    model.sol("sol3").study("std2");
    model.sol("sol3").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol3");
    model.batch("p1").run("compute");

    model.result("pg4").run();
    model.result().duplicate("pg5", "pg4");
    model.result("pg5").run();
    model.result("pg5").label("\u529b\u5b66\u54cd\u5e94\uff0c\u53c2\u6570\u5316");
    model.result("pg5").set("data", "dset3");
    model.result("pg5").set("legendpos", "upperleft");
    model.result("pg5").run();
    model.result("pg5").feature("ptgr1").set("legend", true);
    model.result("pg5").run();

    model.title("\u539a\u5ea6\u526a\u5207\u6a21\u5f0f\u77f3\u82f1\u632f\u8361\u5668");

    model
         .description("\u672c\u4f8b\u6a21\u62df\u4e00\u79cd\u5728\u539a\u5ea6\u526a\u5207\u6a21\u5f0f\u4e0b\u5de5\u4f5c\u7684\u77f3\u82f1\u632f\u8361\u5668\uff0c\u5e76\u663e\u793a\u5982\u4f55\u6b63\u786e\u8bbe\u7f6e\u5750\u6807\u7cfb\u6765\u63cf\u8ff0 AT \u5207\u578b\u77f3\u82f1\uff0c\u4ee5\u53ca\u5982\u4f55\u6a21\u62df\u8c10\u632f\u9a71\u52a8\u4e0b\u7684\u5668\u4ef6\u54cd\u5e94\u3002\u901a\u8fc7\u6539\u53d8\u5e76\u8054\u7535\u5bb9\u5668\u7684\u7535\u5bb9\uff0c\u53ef\u4ee5\u6539\u53d8\u632f\u8361\u5668\u7684\u8c10\u632f\u9891\u7387\u3002");

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();

    model.label("thickness_shear_quartz_oscillator.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
