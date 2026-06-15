/*
 * argon_gec_icp_boltzmann.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:07 by COMSOL 6.3.0.290. */
public class argon_gec_icp_boltzmann {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Plasma_Module\\Space-Dependent_EEDF_Modeling");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("plas", "ColdPlasma", "geom1");
    model.component("comp1").physics().create("mf", "InductionCurrents", "geom1");
    model.component("comp1").physics("mf").prop("ShapeProperty").set("order_magneticvectorpotential", "1");
    model.component("comp1").physics("mf").create("alf1", "AmperesLawFluid");
    model.component("comp1").physics("mf").feature("alf1").selection().all();

    model.component("comp1").multiphysics().create("pcc1", "PlasmaConductivityMultiphysicsCoupling", 2);
    model.component("comp1").multiphysics("pcc1").set("elm_physics", "mf");
    model.component("comp1").multiphysics("pcc1").set("plas_physics", "plas");
    model.component("comp1").multiphysics().create("ehs1", "ElectronHeatSourceMultiphysicsCoupling", 2);
    model.component("comp1").multiphysics("ehs1").set("elm_physics", "mf");
    model.component("comp1").multiphysics("ehs1").set("plas_physics", "plas");

    model.study().create("std1");
    model.study("std1").create("ftrans", "FrequencyTransient");
    model.study("std1").feature("ftrans").set("initialtime", "0");
    model.study("std1").feature("ftrans").set("freq", "1000");
    model.study("std1").feature("ftrans").set("solnum", "auto");
    model.study("std1").feature("ftrans").set("notsolnum", "auto");
    model.study("std1").feature("ftrans").set("outputmap", new String[]{});
    model.study("std1").feature("ftrans").setSolveFor("/physics/plas", true);
    model.study("std1").feature("ftrans").setSolveFor("/physics/mf", true);
    model.study("std1").feature("ftrans").setSolveFor("/multiphysics/pcc1", true);
    model.study("std1").feature("ftrans").setSolveFor("/multiphysics/ehs1", true);

    model.component("comp1").geom("geom1").insertFile("argon_gec_icp_geom_sequence.mph", "geom1");

    model.component("comp1").selection().create("sel1", "Explicit");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("sel1").label("Walls");
    model.component("comp1").selection("sel1").geom(1);
    model.component("comp1").selection("sel1").set(6, 8, 35, 36, 37, 38, 44, 45, 51, 52, 53, 54, 55, 56);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("Coils");
    model.component("comp1").selection("sel2").set(6, 8, 9, 10, 11);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("Coil Boundaries");
    model.component("comp1").selection("sel3").set(6, 8, 9, 10, 11);
    model.component("comp1").selection("sel3").geom("geom1", 2, 1, new String[]{"exterior"});
    model.component("comp1").selection("sel3").set(6, 8, 9, 10, 11);

    model.param().set("Psp", "1500[W]");
    model.param().descr("Psp", "Power input");
    model.param().set("mueN", "4E24[1/(m*V*s)]");
    model.param().descr("mueN", "Reduced electron mobility");
    model.param().set("T0", "300[K]");
    model.param().descr("T0", "Gas temperature");
    model.param().set("p0", "0.02[torr]");
    model.param().descr("p0", "Gas pressure");

    model.component("comp1").physics("plas").selection().set(3);
    model.component("comp1").physics("plas").create("xsec1", "CrossSectionImport", -1);
    model.component("comp1").physics("plas").feature("xsec1").set("Filepath", "Ar_xsecs.txt");
    model.component("comp1").physics("plas").feature("xsec1").runCommand("importData");
    model.component("comp1").physics("plas").prop("ElectronProperties").set("ReducedProps", true);
    model.component("comp1").physics("plas").create("rxn1", "Reaction", 2);
    model.component("comp1").physics("plas").feature("rxn1").set("formula", "Ars+Ars=>e+Ar+Ar+");
    model.component("comp1").physics("plas").feature("rxn1").set("kf", "3.734E8");
    model.component("comp1").physics("plas").create("rxn2", "Reaction", 2);
    model.component("comp1").physics("plas").feature("rxn2").set("formula", "Ars+Ar=>Ar+Ar");
    model.component("comp1").physics("plas").feature("rxn2").set("kf", 1807);
    model.component("comp1").physics("plas").feature("Ar").set("FromMassConstraint", true);
    model.component("comp1").physics("plas").feature("Ar_1p").set("InitIon", true);
    model.component("comp1").physics("plas").feature("init1").set("neinit", "1E15");
    model.component("comp1").physics("plas").feature("init1").set("ebarinit", 5);
    model.component("comp1").physics("mf").selection().set(3, 4, 5, 6, 8, 9, 10, 11, 12);
    model.component("comp1").physics("mf").create("coil1", "Coil", 2);
    model.component("comp1").physics("mf").feature("coil1").selection().named("sel2");
    model.component("comp1").physics("mf").feature("coil1").set("CoilExcitation", "Power");
    model.component("comp1").physics("mf").feature("coil1").set("coilGroup", true);
    model.component("comp1").physics("mf").feature("coil1").set("PCoil", "Psp");
    model.component("comp1").physics("plas").feature("pes1").set("T", "T0");
    model.component("comp1").physics("plas").feature("pes1").set("pA", "p0");
    model.component("comp1").physics("plas").feature("pes1")
         .set("muN", new String[]{"mueN", "0", "0", "0", "mueN", "0", "0", "0", "mueN"});

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").selection().named("sel2");
    model.component("comp1").material("mat1").propertyGroup("def").set("electricconductivity", new String[]{"6e7"});
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermittivity", new String[]{"1"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").selection().set(5);
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("electricconductivity", new String[]{"0"});
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermittivity", new String[]{"1"});
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").selection().set(4, 12);
    model.component("comp1").material("mat3").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat3").propertyGroup("def").set("electricconductivity", new String[]{"0"});
    model.component("comp1").material("mat3").propertyGroup("def").set("relpermittivity", new String[]{"4.2"});

    model.component("comp1").physics("plas").create("sr1", "SurfaceReaction", 1);
    model.component("comp1").physics("plas").feature("sr1").set("formula", "Ars=>Ar");
    model.component("comp1").physics("plas").feature("sr1").selection().named("sel1");
    model.component("comp1").physics("plas").create("sr2", "SurfaceReaction", 1);
    model.component("comp1").physics("plas").feature("sr2").set("formula", "Ar+=>Ar");
    model.component("comp1").physics("plas").feature("sr2").selection().named("sel1");
    model.component("comp1").physics("plas").create("wall1", "WallDriftDiffusion", 1);
    model.component("comp1").physics("plas").feature("wall1").set("re", 0.2);
    model.component("comp1").physics("plas").feature("wall1").selection().named("sel1");
    model.component("comp1").physics("plas").create("gnd1", "Ground", 1);
    model.component("comp1").physics("plas").feature("gnd1").selection().named("sel1");

    model.component("comp1").mesh("mesh1").autoMeshSize(3);
    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").selection().set(6, 8, 44, 45, 54);
    model.component("comp1").mesh("mesh1").feature("edg1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").selection().geom("geom1");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("hmax", "1E-3");
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(3);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hauto", 2);
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom(2);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().allGeom();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set(3);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection().named("sel1");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blnlayers", 5);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blstretch", 1.4);
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map1").selection().named("sel2");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().named("sel3");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemcount", 25);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemratio", 20);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("growthrate", "exponential");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("symmetric", true);
    model.component("comp1").mesh("mesh1").create("ftri2", "FreeTri");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("ftrans").set("tlist", "0 10^{range(-8,5/20,-3)}");
    model.study("std1").feature("ftrans").set("freq", "13.56E6");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 22, 0);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"plas.ne"});
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 22, 0);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"plas.Te"});
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").setIndex("looplevel", 22, 0);
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"V"});
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result("pg2").feature("surf1").set("colortable", "Prism");
    model.result("pg3").feature("surf1").set("colortable", "Dipole");
    model.result("pg1").label("Electron Density (plas)");
    model.result("pg2").label("Electron Temperature (plas)");
    model.result("pg3").label("Electric Potential (plas)");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").label("Magnetic Flux Density (mf)");
    model.result("pg4").set("dataisaxisym", "off");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").set("showlegendsmaxmin", true);
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("solutionparams", "parent");
    model.result("pg4").feature("surf1").set("expr", "mf.normB");
    model.result("pg4").feature("surf1").set("colortabletrans", "nonlinear");
    model.result("pg4").feature("surf1").set("colorcalibration", -0.8);
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result("pg4").feature().create("str1", "Streamline");
    model.result("pg4").feature("str1").set("showsolutionparams", "on");
    model.result("pg4").feature("str1").set("solutionparams", "parent");
    model.result("pg4").feature("str1").set("expr", new String[]{"mf.Br", "mf.Bz"});
    model.result("pg4").feature("str1").set("titletype", "none");
    model.result("pg4").feature("str1").set("posmethod", "uniform");
    model.result("pg4").feature("str1").set("udist", 0.03);
    model.result("pg4").feature("str1").set("maxlen", 0.4);
    model.result("pg4").feature("str1").set("maxsteps", 5000);
    model.result("pg4").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("str1").set("inheritcolor", false);
    model.result("pg4").feature("str1").set("showsolutionparams", "on");
    model.result("pg4").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("str1").set("showsolutionparams", "on");
    model.result("pg4").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("str1").set("showsolutionparams", "on");
    model.result("pg4").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("str1").set("showsolutionparams", "on");
    model.result("pg4").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("str1").set("data", "parent");
    model.result("pg4").feature("str1").selection().geom("geom1", 1);
    model.result("pg4").feature("str1").selection()
         .set(5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56);
    model.result("pg4").feature("str1").set("inheritplot", "surf1");
    model.result("pg4").feature("str1").feature().create("col1", "Color");
    model.result("pg4").feature("str1").feature("col1").set("expr", "mf.normB");
    model.result("pg4").feature("str1").feature("col1").set("colortable", "PrismDark");
    model.result("pg4").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg4").feature("str1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg4").feature("str1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg4").feature("str1").feature().create("filt1", "Filter");
    model.result("pg4").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg4").feature().create("con1", "Contour");
    model.result("pg4").feature("con1").set("showsolutionparams", "on");
    model.result("pg4").feature("con1").set("solutionparams", "parent");
    model.result("pg4").feature("con1").set("expr", "mf.Psi");
    model.result("pg4").feature("con1").set("titletype", "none");
    model.result("pg4").feature("con1").set("number", 10);
    model.result("pg4").feature("con1").set("levelrounding", false);
    model.result("pg4").feature("con1").set("coloring", "uniform");
    model.result("pg4").feature("con1").set("colorlegend", false);
    model.result("pg4").feature("con1").set("color", "custom");
    model.result("pg4").feature("con1")
         .set("customcolor", new double[]{0.3764705955982208, 0.3764705955982208, 0.3764705955982208});
    model.result("pg4").feature("con1").set("resolution", "fine");
    model.result("pg4").feature("con1").set("inheritcolor", false);
    model.result("pg4").feature("con1").set("showsolutionparams", "on");
    model.result("pg4").feature("con1").set("data", "parent");
    model.result("pg4").feature("con1").set("inheritplot", "surf1");
    model.result("pg4").feature("con1").feature().create("filt1", "Filter");
    model.result("pg4").feature("con1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").set("data", "none");
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev1").set("data", "dset1");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").label("Magnetic Flux Density, Revolved Geometry (mf)");
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").set("showlegendsmaxmin", true);
    model.result("pg5").feature().create("vol1", "Volume");
    model.result("pg5").feature("vol1").set("showsolutionparams", "on");
    model.result("pg5").feature("vol1").set("solutionparams", "parent");
    model.result("pg5").feature("vol1").set("expr", "mf.normB");
    model.result("pg5").feature("vol1").set("colortabletrans", "nonlinear");
    model.result("pg5").feature("vol1").set("colorcalibration", -0.8);
    model.result("pg5").feature("vol1").set("showsolutionparams", "on");
    model.result("pg5").feature("vol1").set("data", "parent");
    model.result("pg5").feature().create("con1", "Contour");
    model.result("pg5").feature("con1").set("showsolutionparams", "on");
    model.result("pg5").feature("con1").set("solutionparams", "parent");
    model.result("pg5").feature("con1").set("expr", "mf.Psi");
    model.result("pg5").feature("con1").set("titletype", "none");
    model.result("pg5").feature("con1").set("number", 10);
    model.result("pg5").feature("con1").set("levelrounding", false);
    model.result("pg5").feature("con1").set("coloring", "uniform");
    model.result("pg5").feature("con1").set("colorlegend", false);
    model.result("pg5").feature("con1").set("color", "custom");
    model.result("pg5").feature("con1")
         .set("customcolor", new double[]{0.3764705955982208, 0.3764705955982208, 0.3764705955982208});
    model.result("pg5").feature("con1").set("resolution", "fine");
    model.result("pg5").feature("con1").set("inheritcolor", false);
    model.result("pg5").feature("con1").set("showsolutionparams", "on");
    model.result("pg5").feature("con1").set("data", "parent");
    model.result("pg5").feature("con1").set("inheritplot", "vol1");
    model.result("pg5").feature("con1").feature().create("filt1", "Filter");
    model.result("pg5").feature("con1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg5").feature("con1").feature("filt1").set("shownodespec", "on");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg3").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("Coil Resistance");
    model.result("pg6").set("xlabelactive", true);
    model.result("pg6").set("xlabel", "Time (s)");
    model.result("pg6").set("ylabelactive", true);
    model.result("pg6").set("ylabel", "Coil resistance (Ohm)");
    model.result("pg6").create("glob1", "Global");
    model.result("pg6").feature("glob1").set("markerpos", "datapoints");
    model.result("pg6").feature("glob1").set("linewidth", "preference");
    model.result("pg6").feature("glob1").setIndex("expr", "mf.RCoil_1", 0);
    model.result("pg6").feature("glob1").setIndex("unit", "\u03a9", 0);
    model.result("pg6").feature("glob1").setIndex("descr", "Coil resistance", 0);
    model.result("pg6").set("xlog", true);
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").label("Coil Power");
    model.result("pg7").set("xlabelactive", true);
    model.result("pg7").set("xlabel", "Time (s)");
    model.result("pg7").set("ylabelactive", true);
    model.result("pg7").set("ylabel", "Power (W)");
    model.result("pg7").create("glob1", "Global");
    model.result("pg7").feature("glob1").set("markerpos", "datapoints");
    model.result("pg7").feature("glob1").set("linewidth", "preference");
    model.result("pg7").feature("glob1").setIndex("expr", "mf.PCoil_1", 0);
    model.result("pg7").feature("glob1").setIndex("unit", "W", 0);
    model.result("pg7").feature("glob1").setIndex("descr", "Coil power", 0);
    model.result("pg7").set("xlog", true);
    model.result("pg7").run();
    model.result().create("pg8", "PlotGroup2D");
    model.result("pg8").run();
    model.result("pg8").label("Ion Number Density");
    model.result("pg8").create("surf1", "Surface");
    model.result("pg8").feature("surf1").set("expr", "plas.n_wAr_1p");
    model.result("pg8").feature("surf1").set("descr", "Number density");
    model.result("pg8").run();
    model.result("pg8").run();
    model.result().duplicate("pg9", "pg8");
    model.result("pg9").run();
    model.result("pg9").label("High Frequency Electric Field");
    model.result("pg9").run();
    model.result("pg9").feature("surf1").set("expr", "mf.normE");
    model.result("pg9").feature("surf1").set("descr", "Electric field norm");
    model.result("pg9").run();
    model.result("pg9").run();
    model.result().duplicate("pg10", "pg9");
    model.result("pg10").run();
    model.result("pg10").label("Excited Argon Number Density");
    model.result("pg10").run();
    model.result("pg10").feature("surf1").set("expr", "plas.n_wArs");
    model.result("pg10").feature("surf1").set("descr", "Number density");
    model.result("pg10").run();
    model.result().create("pg11", "PlotGroup2D");
    model.result("pg11").run();
    model.result("pg11").label("Power Deposition");
    model.result("pg11").create("surf1", "Surface");
    model.result("pg11").feature("surf1").set("expr", "mf.Qrh");
    model.result("pg11").feature("surf1").set("descr", "Volumetric loss density, electric");
    model.result("pg11").feature("surf1").set("colortable", "ThermalWave");
    model.result("pg11").feature("surf1").create("sel1", "Selection");
    model.result("pg11").feature("surf1").feature("sel1").selection().set(3);
    model.result("pg11").run();
    model.result("pg11").run();

    model.title("GEC ICP \u53cd\u5e94\u5668\uff0c\u6c29\u5316\u5b66");

    model
         .description("\u672c\u4f8b\u7814\u7a76\u6c29\u5316\u5b66 GEC \u53c2\u8003\u7535\u6c60\u7684\u7535\u7279\u6027\u3002");

    model.label("argon_gec_icp.mph");

    model.result("pg11").run();

    model.component("comp1").physics("plas").prop("EEDFSettings").set("eedf", "BoltzmannTwoTerm");
    model.component("comp1").physics("plas").prop("EEDFSettings").set("eecolls", true);
    model.component("comp1").physics("plas").prop("EEDFSettings").set("oscillating", true);
    model.component("comp1").physics("plas").prop("EEDFSettings").set("omegaN", "2.11E-14[m^3/s]");
    model.component("comp1").physics("plas").prop("EEDFSettings").set("NelemEEDF", 50);
    model.component("comp1").physics("plas").prop("EEDFSettings").set("RelemEEDF", 30);
    model.component("comp1").physics("plas").prop("EEDFSettings").set("computeMaxEnergy", true);
    model.component("comp1").physics("plas").feature("pes1").set("SpecifyElectronDensityAndEnergy", "MueFromEEDF");

    model.component("comp1").multiphysics("pcc1").set("SpecifyCollisionFrequencyUsing", "eedf");

    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hauto", 5);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u9ea6\u514b\u65af\u97e6 EEDF");

    model.result("pg1").run();

    model.nodeGroup().create("grp1", "Results");
    model.nodeGroup("grp1").set("type", "plotgroup");
    model.nodeGroup("grp1").add("plotgroup", "pg1");
    model.nodeGroup("grp1").add("plotgroup", "pg2");
    model.nodeGroup("grp1").add("plotgroup", "pg3");
    model.nodeGroup("grp1").add("plotgroup", "pg4");
    model.nodeGroup("grp1").add("plotgroup", "pg5");
    model.nodeGroup("grp1").add("plotgroup", "pg6");
    model.nodeGroup("grp1").add("plotgroup", "pg7");
    model.nodeGroup("grp1").add("plotgroup", "pg8");
    model.nodeGroup("grp1").add("plotgroup", "pg9");
    model.nodeGroup("grp1").add("plotgroup", "pg10");
    model.nodeGroup("grp1").add("plotgroup", "pg11");
    model.nodeGroup("grp1").label("\u9ea6\u514b\u65af\u97e6 EEDF");

    model.result("pg1").run();
    model.result("pg1").set("titletype", "manual");
    model.result("pg1")
         .set("title", "\u7535\u5b50\u5bc6\u5ea6 (1/m<sup>3</sup>)\uff0c\u9ea6\u514b\u65af\u97e6 EEDF");
    model.result("pg2").run();
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("title", "\u7535\u5b50\u6e29\u5ea6 (V)\uff0c\u9ea6\u514b\u65af\u97e6 EEDF");
    model.result("pg10").run();
    model.result("pg10").set("titletype", "manual");
    model.result("pg10")
         .set("title", "\u6c29\u6570\u5bc6\u5ea6 (1/m<sup>3</sup>)\uff0c\u9ea6\u514b\u65af\u97e6 EEDF");
    model.result("pg11").run();
    model.result("pg11").set("titletype", "manual");
    model.result("pg11")
         .set("title", "\u4f53\u79ef\u635f\u8017\u5bc6\u5ea6\uff0c\u7535 (W/m<sup>3</sup>)\uff0c\u9ea6\u514b\u65af\u97e6 EEDF");

    model.study().create("std2");
    model.study("std2").create("edfi", "EEDFInitialization");
    model.study("std2").feature("edfi").set("plotgroup", "Default");
    model.study("std2").feature("edfi").set("solnum", "auto");
    model.study("std2").feature("edfi").set("notsolnum", "auto");
    model.study("std2").feature("edfi").set("outputmap", new String[]{});
    model.study("std2").feature("edfi").set("ngenAUX", "1");
    model.study("std2").feature("edfi").set("goalngenAUX", "1");
    model.study("std2").feature("edfi").set("ngenAUX", "1");
    model.study("std2").feature("edfi").set("goalngenAUX", "1");
    model.study("std2").feature("edfi").setSolveFor("/physics/plas", true);
    model.study("std2").feature("edfi").setSolveFor("/physics/mf", false);
    model.study("std2").feature("edfi").setSolveFor("/multiphysics/pcc1", false);
    model.study("std2").feature("edfi").setSolveFor("/multiphysics/ehs1", false);
    model.study("std2").label("EEDF \u521d\u59cb\u5316");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().dataset().create("dset3", "Solution");
    model.result().dataset("dset3").set("solution", "sol2");
    model.result().dataset("dset3").set("geom", "plas_eedf_xdim");
    model.result().create("pg12", "PlotGroup1D");
    model.result("pg12").set("data", "dset3");
    model.result("pg12").set("ylog", true);
    model.result("pg12").set("xlabelactive", true);
    model.result("pg12").set("ylabelactive", true);
    model.result("pg12").set("xlabel", "Scaled energy coordinate at reactor center");
    model.result("pg12").set("ylabel", "EEDF at reactor center");
    model.result("pg12").set("titletype", "label");
    model.result("pg12").create("lngr1", "LineGraph");
    model.result("pg12").feature("lngr1").set("xdata", "expr");
    model.result("pg12").feature("lngr1").set("xdataexpr", new String[]{"atxd2(plas.rcr,plas.rcz,plas.xeedf)"});
    model.result("pg12").feature("lngr1").selection().all();
    model.result("pg12").feature("lngr1").label("Two-term Boltzmann");
    model.result("pg12").feature("lngr1").set("legend", true);
    model.result("pg12").feature("lngr1").set("autosolution", false);
    model.result("pg12").feature("lngr1").set("autoplotlabel", true);
    model.result("pg12").feature("lngr1").set("expr", new String[]{"atxd2(plas.rcr,plas.rcz,plas.fcap)"});
    model.result("pg12").create("lngr2", "LineGraph");
    model.result("pg12").feature("lngr2").set("xdata", "expr");
    model.result("pg12").feature("lngr2").set("xdataexpr", new String[]{"atxd2(plas.rcr,plas.rcz,plas.xeedf)"});
    model.result("pg12").feature("lngr2").selection().all();
    model.result("pg12").feature("lngr2").set("expr", new String[]{"atxd2(plas.rcr,plas.rcz,plas.fmax)"});
    model.result("pg12").feature("lngr2").label("Maxwellian");
    model.result("pg12").feature("lngr2").set("legend", true);
    model.result("pg12").feature("lngr2").set("autosolution", false);
    model.result("pg12").feature("lngr2").set("autoplotlabel", true);
    model.result("pg12").create("lngr3", "LineGraph");
    model.result("pg12").feature("lngr3").set("xdata", "expr");
    model.result("pg12").feature("lngr3").set("xdataexpr", new String[]{"atxd2(plas.rcr,plas.rcz,plas.xeedf)"});
    model.result("pg12").feature("lngr3").selection().all();
    model.result("pg12").feature("lngr3").set("expr", new String[]{"atxd2(plas.rcr,plas.rcz,plas.fdruy)"});
    model.result("pg12").feature("lngr3").label("Druyvesteyn");
    model.result("pg12").feature("lngr3").set("legend", true);
    model.result("pg12").feature("lngr3").set("autosolution", false);
    model.result("pg12").feature("lngr3").set("autoplotlabel", true);
    model.result("pg12").create("lngr4", "LineGraph");
    model.result("pg12").feature("lngr4").set("xdata", "expr");
    model.result("pg12").feature("lngr4").set("xdataexpr", new String[]{"atxd2(plas.rcr,plas.rcz,plas.xeedf)"});
    model.result("pg12").feature("lngr4").selection().all();
    model.result("pg12").feature("lngr4").set("expr", new String[]{"atxd2(plas.rcr,plas.rcz,plas.fgen)"});
    model.result("pg12").feature("lngr4").label("Generalized (g=3)");
    model.result("pg12").feature("lngr4").set("legend", true);
    model.result("pg12").feature("lngr4").set("autosolution", false);
    model.result("pg12").feature("lngr4").set("autoplotlabel", true);
    model.result("pg12").label("\u7535\u5b50\u80fd\u91cf\u5206\u5e03\u51fd\u6570 (plas)");
    model.result("pg12").run();
    model.result("pg12").label("\u7535\u5b50\u80fd\u91cf\u5206\u5e03\u51fd\u6570\uff0c\u521d\u59cb\u5316");

    model.study().create("std3");
    model.study("std3").create("ftrans", "FrequencyTransient");
    model.study("std3").feature("ftrans").set("plotgroup", "Default");
    model.study("std3").feature("ftrans").set("initialtime", "0");
    model.study("std3").feature("ftrans").set("freq", "1000");
    model.study("std3").feature("ftrans").set("solnum", "auto");
    model.study("std3").feature("ftrans").set("notsolnum", "auto");
    model.study("std3").feature("ftrans").set("outputmap", new String[]{});
    model.study("std3").feature("ftrans").setSolveFor("/physics/plas", true);
    model.study("std3").feature("ftrans").setSolveFor("/physics/mf", true);
    model.study("std3").feature("ftrans").setSolveFor("/multiphysics/pcc1", true);
    model.study("std3").feature("ftrans").setSolveFor("/multiphysics/ehs1", true);
    model.study("std3").feature("ftrans").set("tlist", "0 10^{range(-8,5/20,-3)}");
    model.study("std3").feature("ftrans").set("freq", "13.56e6");

    return model;
  }

  public static Model run2(Model model) {
    model.study("std3").feature("ftrans").set("useinitsol", true);
    model.study("std3").feature("ftrans").set("initmethod", "sol");
    model.study("std3").feature("ftrans").set("initstudy", "std2");
    model.study("std3").label("\u8ba1\u7b97\u7684 EEDF");
    model.study("std3").createAutoSequences("sol");
    model.study("std3").createAutoSequences("jobs");

    model.sol("sol3").runFromTo("st1", "v1");

    model.result().dataset().create("dset5", "Solution");
    model.result().dataset("dset5").set("solution", "sol3");
    model.result().dataset("dset5").set("geom", "plas_eedf_xdim");
    model.result().create("pg13", "PlotGroup1D");
    model.result("pg13").set("data", "dset5");
    model.result("pg13").set("ylog", true);
    model.result("pg13").set("xlabelactive", true);
    model.result("pg13").set("ylabelactive", true);
    model.result("pg13").set("xlabel", "Scaled energy coordinate at reactor center");
    model.result("pg13").set("ylabel", "EEDF at reactor center");
    model.result("pg13").set("titletype", "label");
    model.result("pg13").create("lngr1", "LineGraph");
    model.result("pg13").feature("lngr1").set("xdata", "expr");
    model.result("pg13").feature("lngr1").set("xdataexpr", new String[]{"atxd2(plas.rcr,plas.rcz,plas.xeedf)"});
    model.result("pg13").feature("lngr1").selection().all();
    model.result("pg13").feature("lngr1").label("Two-term Boltzmann");
    model.result("pg13").feature("lngr1").set("legend", true);
    model.result("pg13").feature("lngr1").set("autosolution", false);
    model.result("pg13").feature("lngr1").set("autoplotlabel", true);
    model.result("pg13").feature("lngr1").set("expr", new String[]{"atxd2(plas.rcr,plas.rcz,plas.fcap)"});
    model.result("pg13").create("lngr2", "LineGraph");
    model.result("pg13").feature("lngr2").set("xdata", "expr");
    model.result("pg13").feature("lngr2").set("xdataexpr", new String[]{"atxd2(plas.rcr,plas.rcz,plas.xeedf)"});
    model.result("pg13").feature("lngr2").selection().all();
    model.result("pg13").feature("lngr2").set("expr", new String[]{"atxd2(plas.rcr,plas.rcz,plas.fmax)"});
    model.result("pg13").feature("lngr2").label("Maxwellian");
    model.result("pg13").feature("lngr2").set("legend", true);
    model.result("pg13").feature("lngr2").set("autosolution", false);
    model.result("pg13").feature("lngr2").set("autoplotlabel", true);
    model.result("pg13").create("lngr3", "LineGraph");
    model.result("pg13").feature("lngr3").set("xdata", "expr");
    model.result("pg13").feature("lngr3").set("xdataexpr", new String[]{"atxd2(plas.rcr,plas.rcz,plas.xeedf)"});
    model.result("pg13").feature("lngr3").selection().all();
    model.result("pg13").feature("lngr3").set("expr", new String[]{"atxd2(plas.rcr,plas.rcz,plas.fdruy)"});
    model.result("pg13").feature("lngr3").label("Druyvesteyn");
    model.result("pg13").feature("lngr3").set("legend", true);
    model.result("pg13").feature("lngr3").set("autosolution", false);
    model.result("pg13").feature("lngr3").set("autoplotlabel", true);
    model.result("pg13").create("lngr4", "LineGraph");
    model.result("pg13").feature("lngr4").set("xdata", "expr");
    model.result("pg13").feature("lngr4").set("xdataexpr", new String[]{"atxd2(plas.rcr,plas.rcz,plas.xeedf)"});
    model.result("pg13").feature("lngr4").selection().all();
    model.result("pg13").feature("lngr4").set("expr", new String[]{"atxd2(plas.rcr,plas.rcz,plas.fgen)"});
    model.result("pg13").feature("lngr4").label("Generalized (g=3)");
    model.result("pg13").feature("lngr4").set("legend", true);
    model.result("pg13").feature("lngr4").set("autosolution", false);
    model.result("pg13").feature("lngr4").set("autoplotlabel", true);
    model.result("pg13").label("\u7535\u5b50\u80fd\u91cf\u5206\u5e03\u51fd\u6570 (plas)");
    model.result().create("pg14", "PlotGroup2D");
    model.result("pg14").set("data", "dset4");
    model.result("pg14").create("surf1", "Surface");
    model.result("pg14").feature("surf1").set("expr", new String[]{"plas.ne"});
    model.result().create("pg15", "PlotGroup2D");
    model.result("pg15").set("data", "dset4");
    model.result("pg15").create("surf1", "Surface");
    model.result("pg15").feature("surf1").set("expr", new String[]{"plas.Te"});
    model.result().create("pg16", "PlotGroup2D");
    model.result("pg16").set("data", "dset4");
    model.result("pg16").create("surf1", "Surface");
    model.result("pg16").feature("surf1").set("expr", new String[]{"V"});
    model.result("pg14").feature("surf1").set("colortable", "Prism");
    model.result("pg15").feature("surf1").set("colortable", "Prism");
    model.result("pg16").feature("surf1").set("colortable", "Dipole");
    model.result("pg14").label("\u7535\u5b50\u5bc6\u5ea6 (plas)");
    model.result("pg15").label("\u7535\u5b50\u6e29\u5ea6 (plas)");
    model.result("pg16").label("\u7535\u52bf (plas)");
    model.result().create("pg17", "PlotGroup2D");
    model.result("pg17").label("\u78c1\u901a\u5bc6\u5ea6 (mf)");
    model.result("pg17").set("data", "dset4");
    model.result("pg17").set("dataisaxisym", "off");
    model.result("pg17").set("frametype", "spatial");
    model.result("pg17").set("showlegendsmaxmin", true);
    model.result("pg17").feature().create("surf1", "Surface");
    model.result("pg17").feature("surf1").set("showsolutionparams", "on");
    model.result("pg17").feature("surf1").set("solutionparams", "parent");
    model.result("pg17").feature("surf1").set("expr", "mf.normB");
    model.result("pg17").feature("surf1").set("colortabletrans", "nonlinear");
    model.result("pg17").feature("surf1").set("colorcalibration", -0.8);
    model.result("pg17").feature("surf1").set("showsolutionparams", "on");
    model.result("pg17").feature("surf1").set("data", "parent");
    model.result("pg17").feature().create("str1", "Streamline");
    model.result("pg17").feature("str1").set("showsolutionparams", "on");
    model.result("pg17").feature("str1").set("solutionparams", "parent");
    model.result("pg17").feature("str1").set("expr", new String[]{"mf.Br", "mf.Bz"});
    model.result("pg17").feature("str1").set("titletype", "none");
    model.result("pg17").feature("str1").set("posmethod", "uniform");
    model.result("pg17").feature("str1").set("udist", 0.03);
    model.result("pg17").feature("str1").set("maxlen", 0.4);
    model.result("pg17").feature("str1").set("maxsteps", 5000);
    model.result("pg17").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg17").feature("str1").set("inheritcolor", false);
    model.result("pg17").feature("str1").set("showsolutionparams", "on");
    model.result("pg17").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg17").feature("str1").set("showsolutionparams", "on");
    model.result("pg17").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg17").feature("str1").set("showsolutionparams", "on");
    model.result("pg17").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg17").feature("str1").set("showsolutionparams", "on");
    model.result("pg17").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg17").feature("str1").set("data", "parent");
    model.result("pg17").feature("str1").selection().geom("geom1", 1);
    model.result("pg17").feature("str1").selection()
         .set(5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56);
    model.result("pg17").feature("str1").set("inheritplot", "surf1");
    model.result("pg17").feature("str1").feature().create("col1", "Color");
    model.result("pg17").feature("str1").feature("col1").set("expr", "mf.normB");
    model.result("pg17").feature("str1").feature("col1").set("colortable", "PrismDark");
    model.result("pg17").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg17").feature("str1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg17").feature("str1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg17").feature("str1").feature().create("filt1", "Filter");
    model.result("pg17").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg17").feature().create("con1", "Contour");
    model.result("pg17").feature("con1").set("showsolutionparams", "on");
    model.result("pg17").feature("con1").set("solutionparams", "parent");
    model.result("pg17").feature("con1").set("expr", "mf.Psi");
    model.result("pg17").feature("con1").set("titletype", "none");
    model.result("pg17").feature("con1").set("number", 10);
    model.result("pg17").feature("con1").set("levelrounding", false);
    model.result("pg17").feature("con1").set("coloring", "uniform");
    model.result("pg17").feature("con1").set("colorlegend", false);
    model.result("pg17").feature("con1").set("color", "custom");
    model.result("pg17").feature("con1")
         .set("customcolor", new double[]{0.3764705955982208, 0.3764705955982208, 0.3764705955982208});
    model.result("pg17").feature("con1").set("resolution", "fine");
    model.result("pg17").feature("con1").set("inheritcolor", false);
    model.result("pg17").feature("con1").set("showsolutionparams", "on");
    model.result("pg17").feature("con1").set("data", "parent");
    model.result("pg17").feature("con1").set("inheritplot", "surf1");
    model.result("pg17").feature("con1").feature().create("filt1", "Filter");
    model.result("pg17").feature("con1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().dataset().create("rev2", "Revolve2D");
    model.result().dataset("rev2").set("data", "none");
    model.result().dataset("rev2").set("startangle", -90);
    model.result().dataset("rev2").set("revangle", 225);
    model.result().dataset("rev2").set("data", "dset4");
    model.result().create("pg18", "PlotGroup3D");
    model.result("pg18").label("\u78c1\u901a\u5bc6\u5ea6\uff0c\u56de\u8f6c\u51e0\u4f55 (mf)");
    model.result("pg18").set("data", "rev2");
    model.result("pg18").set("frametype", "spatial");
    model.result("pg18").set("showlegendsmaxmin", true);
    model.result("pg18").feature().create("vol1", "Volume");
    model.result("pg18").feature("vol1").set("showsolutionparams", "on");
    model.result("pg18").feature("vol1").set("solutionparams", "parent");
    model.result("pg18").feature("vol1").set("expr", "mf.normB");
    model.result("pg18").feature("vol1").set("colortabletrans", "nonlinear");
    model.result("pg18").feature("vol1").set("colorcalibration", -0.8);
    model.result("pg18").feature("vol1").set("showsolutionparams", "on");
    model.result("pg18").feature("vol1").set("data", "parent");
    model.result("pg18").feature().create("con1", "Contour");
    model.result("pg18").feature("con1").set("showsolutionparams", "on");
    model.result("pg18").feature("con1").set("solutionparams", "parent");
    model.result("pg18").feature("con1").set("expr", "mf.Psi");
    model.result("pg18").feature("con1").set("titletype", "none");
    model.result("pg18").feature("con1").set("number", 10);
    model.result("pg18").feature("con1").set("levelrounding", false);
    model.result("pg18").feature("con1").set("coloring", "uniform");
    model.result("pg18").feature("con1").set("colorlegend", false);
    model.result("pg18").feature("con1").set("color", "custom");
    model.result("pg18").feature("con1")
         .set("customcolor", new double[]{0.3764705955982208, 0.3764705955982208, 0.3764705955982208});
    model.result("pg18").feature("con1").set("resolution", "fine");
    model.result("pg18").feature("con1").set("inheritcolor", false);
    model.result("pg18").feature("con1").set("showsolutionparams", "on");
    model.result("pg18").feature("con1").set("data", "parent");
    model.result("pg18").feature("con1").set("inheritplot", "vol1");
    model.result("pg18").feature("con1").feature().create("filt1", "Filter");
    model.result("pg18").feature("con1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg18").feature("con1").feature("filt1").set("shownodespec", "on");
    model.result("pg13").run();

    model.nodeGroup().create("grp2", "Results");
    model.nodeGroup("grp2").set("type", "plotgroup");
    model.nodeGroup("grp2").placeAfter("plotgroup", "pg12");
    model.nodeGroup("grp2").add("plotgroup", "pg13");
    model.nodeGroup("grp2").add("plotgroup", "pg14");
    model.nodeGroup("grp2").add("plotgroup", "pg15");
    model.nodeGroup("grp2").add("plotgroup", "pg16");
    model.nodeGroup("grp2").add("plotgroup", "pg17");
    model.nodeGroup("grp2").add("plotgroup", "pg18");
    model.nodeGroup("grp2").label("\u8ba1\u7b97\u7684 EEDF");

    model.sol("sol3").feature("t1").feature("se1").set("plot", true);
    model.sol("sol3").feature("t1").feature("se1").set("plotgroup", "pg15");

    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result("pg13").run();
    model.result("pg13").label("\u8ba1\u7b97\u7684 EEDF r=3 cm");
    model.result("pg13").setIndex("looplevelinput", "last", 0);
    model.result("pg13").set("titletype", "none");
    model.result("pg13").set("xlabel", "\u80fd\u91cf (eV)");
    model.result("pg13").set("ylabel", "EEDF (eV<sup>-3/2</sup>)");
    model.result("pg13").set("axislimits", true);
    model.result("pg13").set("xmin", 0);
    model.result("pg13").set("xmax", 80);
    model.result("pg13").set("ymin", "1e-7");
    model.result("pg13").set("ymax", 0.2);
    model.result("pg13").run();
    model.result("pg13").feature("lngr1").label("\u4e24\u9879\u73bb\u5c14\u5179\u66fc z=0.5 cm");
    model.result("pg13").feature("lngr1").set("expr", "atxd2(0.03,0.005,plas.fcap)");
    model.result("pg13").feature("lngr1").set("xdataexpr", "atxd2(0.03,0.005,plas.xeedf)");
    model.result("pg13").feature().duplicate("lngr5", "lngr1");
    model.result("pg13").run();
    model.result("pg13").feature("lngr5").label("\u4e24\u9879\u73bb\u5c14\u5179\u66fc z=1 cm");
    model.result("pg13").feature("lngr5").set("expr", "atxd2(0.03,0.01,plas.fcap)");
    model.result("pg13").feature("lngr5").set("xdataexpr", "atxd2(0.03,0.01,plas.xeedf)");
    model.result("pg13").feature().duplicate("lngr6", "lngr5");
    model.result("pg13").run();
    model.result("pg13").feature("lngr6").label("\u4e24\u9879\u73bb\u5c14\u5179\u66fc z=2 cm");
    model.result("pg13").feature("lngr6").set("expr", "atxd2(0.03,0.02,plas.fcap)");
    model.result("pg13").feature("lngr6").set("xdataexpr", "atxd2(0.03,0.02,plas.xeedf)");
    model.result("pg13").feature().duplicate("lngr7", "lngr6");
    model.result("pg13").run();
    model.result("pg13").feature("lngr7").label("\u4e24\u9879\u73bb\u5c14\u5179\u66fc z=3 cm");
    model.result("pg13").feature("lngr7").set("expr", "atxd2(0.03,0.03,plas.fcap)");
    model.result("pg13").feature("lngr7").set("xdataexpr", "atxd2(0.03,0.03,plas.xeedf)");
    model.result("pg13").feature().duplicate("lngr8", "lngr7");
    model.result("pg13").run();
    model.result("pg13").feature("lngr8").label("\u4e24\u9879\u73bb\u5c14\u5179\u66fc z=3.5 cm");
    model.result("pg13").feature("lngr8").set("expr", "atxd2(0.03,0.035,plas.fcap)");
    model.result("pg13").feature("lngr8").set("xdataexpr", "atxd2(0.03,0.035,plas.xeedf)");
    model.result("pg13").run();
    model.result("pg13").feature("lngr3").active(false);
    model.result("pg13").run();
    model.result("pg13").feature("lngr4").active(false);
    model.result("pg13").run();
    model.result("pg13").feature().move("lngr2", 2);
    model.result("pg13").feature().move("lngr2", 3);
    model.result("pg13").feature().move("lngr2", 4);
    model.result("pg13").feature().move("lngr2", 5);
    model.result("pg13").feature().move("lngr2", 6);
    model.result("pg13").feature().move("lngr2", 7);
    model.result("pg13").feature("lngr2").label("\u9ea6\u514b\u65af\u97e6 EEDF z=3.5 cm");
    model.result("pg13").feature("lngr2").set("expr", "atxd2(0.03,0.035,plas.fmax)");
    model.result("pg13").feature("lngr2").set("xdataexpr", "atxd2(0.03,0.035,plas.xeedf)");
    model.result("pg13").feature("lngr2").set("linestyle", "dashed");
    model.result("pg13").feature("lngr2").set("linecolor", "black");
    model.result("pg14").run();
    model.result().duplicate("pg19", "pg14");

    model.nodeGroup("grp2").add("plotgroup", "pg19");

    model.result("pg19").run();
    model.result("pg19").label("\u6fc0\u53d1\u6001\u6c29\u6570\u5bc6\u5ea6 1");
    model.result("pg19").run();
    model.result("pg19").feature("surf1").set("expr", "plas.n_wArs");
    model.result("pg19").run();
    model.result("pg19").run();
    model.result().duplicate("pg20", "pg19");

    model.nodeGroup("grp2").add("plotgroup", "pg20");

    model.result("pg20").run();
    model.result("pg20").label("\u529f\u7387\u6c89\u79ef 1");
    model.result("pg20").run();
    model.result("pg20").feature("surf1").set("expr", "mf.Qrh");
    model.result("pg20").feature("surf1").set("colortable", "ThermalWave");
    model.result("pg20").feature("surf1").create("sel1", "Selection");
    model.result("pg20").feature("surf1").feature("sel1").selection().set(3);
    model.result("pg20").run();
    model.result("pg14").run();
    model.result("pg14").set("titletype", "manual");
    model.result("pg14").set("title", "\u7535\u5b50\u5bc6\u5ea6 (1/m<sup>3</sup>)\uff0c\u8ba1\u7b97\u7684 EEDF");
    model.result("pg15").run();
    model.result("pg15").set("titletype", "manual");
    model.result("pg15").set("title", "\u7535\u5b50\u6e29\u5ea6 (V)\uff0c\u8ba1\u7b97\u7684 EEDF");
    model.result("pg19").run();
    model.result("pg19").set("titletype", "manual");
    model.result("pg19").set("title", "\u6c29\u6570\u5bc6\u5ea6 (1/m<sup>3</sup>)\uff0c\u8ba1\u7b97 EEDF");
    model.result("pg20").run();
    model.result("pg20").set("titletype", "manual");
    model.result("pg20")
         .set("title", "\u4f53\u79ef\u635f\u8017\u5bc6\u5ea6\uff0c\u7535 (W/m<sup>3</sup>)\uff0c\u8ba1\u7b97 EEDF");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg10").run();
    model.result("pg11").run();
    model.result("pg14").run();
    model.result("pg15").run();
    model.result("pg19").run();
    model.result("pg20").run();
    model.result("pg13").run();

    model
         .title("\u901a\u8fc7\u4e24\u9879\u73bb\u5c14\u5179\u66fc\u65b9\u7a0b\u8026\u5408\u6a21\u62df GEC ICP \u53cd\u5e94\u5668");

    model
         .description("\u672c\u6559\u7a0b\u901a\u8fc7\u6c42\u89e3\u7b49\u79bb\u5b50\u4f53\u6d41\u4f53\u7c7b\u578b\u65b9\u7a0b\u6765\u6a21\u62df ICP \u53cd\u5e94\u5668\uff0c\u8fd9\u7c7b\u65b9\u7a0b\u4e0e\u91c7\u7528\u7ecf\u5178\u4e24\u9879\u8fd1\u4f3c\u7684\u5b9a\u5e38\u9f50\u6b21\u7535\u5b50\u73bb\u5c14\u5179\u66fc\u65b9\u7a0b\u5b8c\u5168\u8026\u5408\u3002\u6211\u4eec\u5bf9\u6bcf\u4e2a\u7a7a\u95f4\u4f4d\u7f6e\u6c42\u89e3\u8fd1\u4f3c\u73bb\u5c14\u5179\u66fc\u65b9\u7a0b\uff0c\u5e76\u901a\u8fc7\u7535\u5b50\u5e73\u5747\u80fd\u91cf\u7684\u65b9\u5f0f\u5c06\u8be5\u65b9\u7a0b\u4e0e\u6d41\u4f53\u7c7b\u578b\u65b9\u7a0b\u8026\u5408\u3002\u901a\u8fc7\u5c06\u8ba1\u7b97\u5f97\u5230\u7684\u7535\u5b50\u80fd\u91cf\u5206\u5e03\u51fd\u6570\u5728\u7535\u5b50\u6563\u5c04\u622a\u9762\u4e0a\u8fdb\u884c\u9002\u5f53\u79ef\u5206\uff0c\u5f97\u5230\u7535\u5b50\u78b0\u649e\u53cd\u5e94\u7684\u901f\u7387\u5e38\u6570\u548c\u7535\u5b50\u4f20\u8f93\u53c2\u6570\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("argon_gec_icp_boltzmann.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
