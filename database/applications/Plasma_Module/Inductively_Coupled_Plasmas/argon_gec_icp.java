/*
 * argon_gec_icp.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:05 by COMSOL 6.3.0.290. */
public class argon_gec_icp {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Plasma_Module\\Inductively_Coupled_Plasmas");

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

    model.component("comp1").selection("sel1").label("\u58c1");
    model.component("comp1").selection("sel1").geom(1);
    model.component("comp1").selection("sel1").set(6, 8, 35, 36, 37, 38, 44, 45, 51, 52, 53, 54, 55, 56);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u7ebf\u5708");
    model.component("comp1").selection("sel2").set(6, 8, 9, 10, 11);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("\u7ebf\u5708\u8fb9\u754c");
    model.component("comp1").selection("sel3").set(6, 8, 9, 10, 11);
    model.component("comp1").selection("sel3").geom("geom1", 2, 1, new String[]{"exterior"});
    model.component("comp1").selection("sel3").set(6, 8, 9, 10, 11);

    model.param().set("Psp", "1500[W]");
    model.param().descr("Psp", "\u529f\u7387\u8f93\u5165");
    model.param().set("mueN", "4E24[1/(m*V*s)]");
    model.param().descr("mueN", "\u7ea6\u5316\u7535\u5b50\u8fc1\u79fb\u7387");
    model.param().set("T0", "300[K]");
    model.param().descr("T0", "\u6c14\u4f53\u6e29\u5ea6");
    model.param().set("p0", "0.02[torr]");
    model.param().descr("p0", "\u6c14\u538b");

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
    model.result("pg1").label("\u7535\u5b50\u5bc6\u5ea6 (plas)");
    model.result("pg2").label("\u7535\u5b50\u6e29\u5ea6 (plas)");
    model.result("pg3").label("\u7535\u52bf (plas)");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").label("\u78c1\u901a\u5bc6\u5ea6 (mf)");
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
    model.result("pg5").label("\u78c1\u901a\u5bc6\u5ea6\uff0c\u56de\u8f6c\u51e0\u4f55 (mf)");
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
    model.result("pg6").label("\u7ebf\u5708\u7535\u963b");
    model.result("pg6").set("xlabelactive", true);
    model.result("pg6").set("xlabel", "\u65f6\u95f4 (s)");
    model.result("pg6").set("ylabelactive", true);
    model.result("pg6").set("ylabel", "\u7ebf\u5708\u7535\u963b (Ohm)");
    model.result("pg6").create("glob1", "Global");
    model.result("pg6").feature("glob1").set("markerpos", "datapoints");
    model.result("pg6").feature("glob1").set("linewidth", "preference");
    model.result("pg6").feature("glob1").setIndex("expr", "mf.RCoil_1", 0);
    model.result("pg6").feature("glob1").setIndex("descr", "\u7ebf\u5708\u7535\u963b", 0);
    model.result("pg6").set("xlog", true);
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").label("\u7ebf\u5708\u529f\u7387");
    model.result("pg7").set("xlabelactive", true);
    model.result("pg7").set("xlabel", "\u65f6\u95f4 (s)");
    model.result("pg7").set("ylabelactive", true);
    model.result("pg7").set("ylabel", "\u529f\u7387 (W)");
    model.result("pg7").create("glob1", "Global");
    model.result("pg7").feature("glob1").set("markerpos", "datapoints");
    model.result("pg7").feature("glob1").set("linewidth", "preference");
    model.result("pg7").feature("glob1").setIndex("expr", "mf.PCoil_1", 0);
    model.result("pg7").set("xlog", true);
    model.result("pg7").run();
    model.result().create("pg8", "PlotGroup2D");
    model.result("pg8").run();
    model.result("pg8").label("\u79bb\u5b50\u6570\u5bc6\u5ea6");
    model.result("pg8").create("surf1", "Surface");
    model.result("pg8").feature("surf1").set("expr", "plas.n_wAr_1p");
    model.result("pg8").feature("surf1").set("descr", "\u6570\u5bc6\u5ea6");
    model.result("pg8").run();
    model.result("pg8").run();
    model.result().duplicate("pg9", "pg8");
    model.result("pg9").run();
    model.result("pg9").label("\u9ad8\u9891\u7535\u573a");
    model.result("pg9").run();
    model.result("pg9").feature("surf1").set("expr", "mf.normE");
    model.result("pg9").feature("surf1").set("descr", "\u7535\u573a\u6a21");
    model.result("pg9").run();
    model.result("pg9").run();
    model.result().duplicate("pg10", "pg9");
    model.result("pg10").run();
    model.result("pg10").label("\u53d7\u6fc0\u6c29\u6570\u5bc6\u5ea6");
    model.result("pg10").run();
    model.result("pg10").feature("surf1").set("expr", "plas.n_wArs");
    model.result("pg10").feature("surf1").set("descr", "\u6570\u5bc6\u5ea6");
    model.result("pg10").run();
    model.result().create("pg11", "PlotGroup2D");
    model.result("pg11").run();
    model.result("pg11").label("\u529f\u7387\u6c89\u79ef");
    model.result("pg11").create("surf1", "Surface");
    model.result("pg11").feature("surf1").set("expr", "mf.Qrh");
    model.result("pg11").feature("surf1").set("descr", "\u4f53\u79ef\u635f\u8017\u5bc6\u5ea6\uff0c\u7535");
    model.result("pg11").feature("surf1").set("colortable", "ThermalWave");
    model.result("pg11").feature("surf1").create("sel1", "Selection");
    model.result("pg11").feature("surf1").feature("sel1").selection().set(3);
    model.result("pg11").run();
    model.result("pg11").run();

    model.title("GEC ICP \u53cd\u5e94\u5668\uff0c\u6c29\u5316\u5b66");

    model
         .description("\u672c\u4f8b\u7814\u7a76\u6c29\u5316\u5b66 GEC \u53c2\u8003\u7535\u6c60\u7684\u7535\u7279\u6027\u3002");

    model.label("argon_gec_icp.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
