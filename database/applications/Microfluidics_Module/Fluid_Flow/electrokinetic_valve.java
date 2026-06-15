/*
 * electrokinetic_valve.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:07 by COMSOL 6.3.0.290. */
public class electrokinetic_valve {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Microfluidics_Module\\Fluid_Flow");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("ec", "ConductiveMedia", "geom1");
    model.component("comp1").physics().create("tds", "DilutedSpecies", "geom1");
    model.component("comp1").physics().create("spf", "CreepingFlow", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/ec", true);
    model.study("std1").feature("stat").setSolveFor("/physics/tds", true);
    model.study("std1").feature("stat").setSolveFor("/physics/spf", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param()
         .set("V_appUB", "-3.2[V]", "\u5916\u52a0\u7535\u538b\uff0c\u4e0a\u65b9\u7f13\u51b2\u6eb6\u6db2\u5165\u53e3");
    model.param().set("V_appS", "-1[V]", "\u5916\u52a0\u7535\u538b\uff0c\u6837\u54c1\u6eb6\u6db2\u5165\u53e3");
    model.param().set("u_a", "0.2[mm/s]", "\u5e73\u5747\u901f\u5ea6\uff0c\u6837\u54c1\u6eb6\u6db2\u5165\u53e3");
    model.param().set("w_a", "0.67[mm/s]", "\u5e73\u5747\u901f\u5ea6\uff0c\u7f13\u51b2\u6eb6\u6db2\u5165\u53e3");
    model.param().set("D", "1e-9[m^2/s]", "\u6837\u54c1\u79bb\u5b50\u6269\u6563\u7cfb\u6570");
    model.param().set("T", "298[K]", "\u6e29\u5ea6");
    model.param()
         .set("c_in", "0.05*3.5[g/l]/(22+35)[g/mol]", "\u6837\u54c1\u79bb\u5b50\u6d53\u5ea6\uff0c\u5165\u53e3");
    model.param().set("z_c", "-1", "\u7535\u8377\u6570");

    model.component("comp1").geom("geom1").insertFile("electrokinetic_valve_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("boxsel5");

    model.component("comp1").view("view1").set("showaxisorientation", false);
    model.component("comp1").view("view1").set("showgrid", false);
    model.component("comp1").view("view1").set("showaxisorientation", true);
    model.component("comp1").view("view1").set("showgrid", true);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u7535\u89e3\u6db2");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"1[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermittivity", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"1e3[kg/m^3]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("dynamicviscosity", new String[]{"1e-3[Pa*s]"});

    model.component("comp1").physics("ec").create("pot1", "ElectricPotential", 2);
    model.component("comp1").physics("ec").feature("pot1")
         .label("\u7535\u52bf - \u805a\u7126\u9636\u6bb5\u548c\u6ce8\u5c04\u9636\u6bb5\u6a21\u5f0f B");
    model.component("comp1").physics("ec").feature("pot1").selection().named("geom1_boxsel1");
    model.component("comp1").physics("ec").feature("pot1").set("V0", "V_appS");
    model.component("comp1").physics("ec").create("gnd1", "Ground", 2);
    model.component("comp1").physics("ec").feature("gnd1")
         .label("\u63a5\u5730 - \u805a\u7126\u9636\u6bb5\u548c\u6ce8\u5c04\u9636\u6bb5\u6a21\u5f0f B");
    model.component("comp1").physics("ec").feature("gnd1").selection().named("geom1_boxsel4");
    model.component("comp1").physics("ec").create("pot2", "ElectricPotential", 2);
    model.component("comp1").physics("ec").feature("pot2").label("\u7535\u52bf - \u6ce8\u5c04\u9636\u6bb5");
    model.component("comp1").physics("ec").feature("pot2").selection().named("geom1_boxsel2");
    model.component("comp1").physics("ec").feature("pot2").set("V0", "V_appUB");
    model.component("comp1").physics("ec").create("gnd2", "Ground", 2);
    model.component("comp1").physics("ec").feature("gnd2").label("\u63a5\u5730 - \u6ce8\u5c04\u9636\u6bb5");
    model.component("comp1").physics("ec").feature("gnd2").selection().named("geom1_boxsel3");
    model.component("comp1").physics("tds").prop("TransportMechanism").set("Migration", true);
    model.component("comp1").physics("tds").prop("ShapeProperty").set("order_concentration", 2);
    model.component("comp1").physics("tds").feature("sp1").setIndex("z", "z_c", 0);
    model.component("comp1").physics("tds").feature("cdm1")
         .label("\u4f20\u9012\u5c5e\u6027 - \u805a\u7126\u9636\u6bb5");
    model.component("comp1").physics("tds").feature("cdm1").set("minput_temperature_src", "userdef");
    model.component("comp1").physics("tds").feature("cdm1").set("minput_temperature", "T");
    model.component("comp1").physics("tds").feature("cdm1").set("u_src", "root.comp1.u");
    model.component("comp1").physics("tds").feature("cdm1").set("V_src", "root.comp1.V");
    model.component("comp1").physics("tds").feature("cdm1")
         .set("D_c", new String[]{"D", "0", "0", "0", "D", "0", "0", "0", "D"});
    model.component("comp1").physics("tds").feature().duplicate("cdm2", "cdm1");
    model.component("comp1").physics("tds").feature("cdm2")
         .label("\u4f20\u9012\u5c5e\u6027 - \u6ce8\u5c04\u9636\u6bb5");
    model.component("comp1").physics("tds").feature("cdm2").set("u_src", "userdef");
    model.component("comp1").physics("tds").create("conc1", "Concentration", 2);
    model.component("comp1").physics("tds").feature("conc1").label("\u6837\u54c1\u5165\u53e3\u7684\u6d53\u5ea6");
    model.component("comp1").physics("tds").feature("conc1").selection().named("geom1_boxsel1");
    model.component("comp1").physics("tds").feature("conc1").setIndex("species", true, 0);
    model.component("comp1").physics("tds").feature("conc1").setIndex("c0", "c_in", 0);
    model.component("comp1").physics("tds").create("conc2", "Concentration", 2);
    model.component("comp1").physics("tds").feature("conc2")
         .label("\u7f13\u51b2\u6eb6\u6db2\u5165\u53e3\u7684\u6d53\u5ea6");
    model.component("comp1").physics("tds").feature("conc2").selection().named("geom1_unisel1");
    model.component("comp1").physics("tds").feature("conc2").setIndex("species", true, 0);
    model.component("comp1").physics("tds").create("out1", "Outflow", 2);
    model.component("comp1").physics("tds").feature("out1").selection().named("geom1_boxsel4");
    model.component("comp1").physics("tds").create("fl1", "FluxBoundary", 2);
    model.component("comp1").physics("tds").feature("fl1")
         .label("\u5165\u53e3\u548c\u51fa\u53e3\u5904\u7684\u8fc1\u79fb - \u6ce8\u5c04\u9636\u6bb5");
    model.component("comp1").physics("tds").feature("fl1").selection().named("geom1_unisel3");
    model.component("comp1").physics("tds").feature("fl1").setIndex("species", true, 0);
    model.component("comp1").physics("tds").feature("fl1").setIndex("J0", "-tds.nmflux_c", 0);
    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 2);
    model.component("comp1").physics("spf").feature("inl1").label("\u5165\u53e3\uff0c\u6837\u54c1");
    model.component("comp1").physics("spf").feature("inl1").selection().named("geom1_boxsel1");
    model.component("comp1").physics("spf").feature("inl1").set("BoundaryCondition", "FullyDevelopedFlow");
    model.component("comp1").physics("spf").feature("inl1").set("Uavfdf", "u_a");
    model.component("comp1").physics("spf").create("inl2", "InletBoundary", 2);
    model.component("comp1").physics("spf").feature("inl2")
         .label("\u5165\u53e3\uff0c\u4e0a\u7f13\u51b2\u6eb6\u6db2");
    model.component("comp1").physics("spf").feature("inl2").selection().named("geom1_boxsel2");
    model.component("comp1").physics("spf").feature("inl2").set("BoundaryCondition", "FullyDevelopedFlow");
    model.component("comp1").physics("spf").feature("inl2").set("Uavfdf", "w_a");
    model.component("comp1").physics("spf").create("inl3", "InletBoundary", 2);
    model.component("comp1").physics("spf").feature("inl3")
         .label("\u5165\u53e3\uff0c\u4e0b\u7f13\u51b2\u6eb6\u6db2");
    model.component("comp1").physics("spf").feature("inl3").selection().named("geom1_boxsel3");
    model.component("comp1").physics("spf").feature("inl3").set("BoundaryCondition", "FullyDevelopedFlow");
    model.component("comp1").physics("spf").feature("inl3").set("Uavfdf", "w_a");
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 2);
    model.component("comp1").physics("spf").feature("out1").selection().named("geom1_boxsel4");
    model.component("comp1").physics("spf").feature("out1").set("NormalFlow", true);

    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", 29);
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", 5);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u7814\u7a76 - \u6a21\u5f0f A");
    model.study("std1").feature("stat").label("\u7a33\u6001 - \u805a\u7126\u9636\u6bb5");
    model.study("std1").feature("stat").setSolveFor("/physics/ec", false);
    model.study("std1").feature("stat").setSolveFor("/physics/tds", false);
    model.study("std1").create("stat2", "Stationary");
    model.study("std1").feature("stat2").label("\u7a33\u6001 2 - \u805a\u7126\u9636\u6bb5");
    model.study("std1").feature("stat2").set("useadvanceddisable", true);
    model.study("std1").feature("stat2").setSolveFor("/physics/tds", false);
    model.study("std1").feature("stat2").setSolveFor("/physics/spf", false);
    model.study("std1").feature("stat2").set("disabledphysics", new String[]{"ec/pot2", "ec/gnd2"});
    model.study("std1").create("stat3", "Stationary");
    model.study("std1").feature("stat3").label("\u7a33\u6001 3 - \u805a\u7126\u9636\u6bb5");
    model.study("std1").feature("stat3").set("useadvanceddisable", true);
    model.study("std1").feature("stat3").setSolveFor("/physics/ec", false);
    model.study("std1").feature("stat3").setSolveFor("/physics/spf", false);
    model.study("std1").feature("stat3").set("disabledphysics", new String[]{"tds/cdm2", "tds/fl1"});
    model.study("std1").create("stat4", "Stationary");
    model.study("std1").feature("stat4").label("\u7a33\u6001 - \u6ce8\u5c04\u9636\u6bb5");
    model.study("std1").feature("stat4").set("useadvanceddisable", true);
    model.study("std1").feature("stat4").set("disabledphysics", new String[]{"ec/pot1", "ec/gnd1"});
    model.study("std1").feature("stat4").setSolveFor("/physics/tds", false);
    model.study("std1").feature("stat4").setSolveFor("/physics/spf", false);
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").label("\u77ac\u6001 - \u6ce8\u5c04\u9636\u6bb5");
    model.study("std1").feature("time").set("tlist", "range(0,0.06,0.6)");
    model.study("std1").feature("time").setSolveFor("/physics/ec", false);
    model.study("std1").feature("time").setSolveFor("/physics/spf", false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u7535\u52bf (ec)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").feature().create("vol1", "Volume");
    model.result("pg1").feature("vol1").set("showsolutionparams", "on");
    model.result("pg1").feature("vol1").set("solutionparams", "parent");
    model.result("pg1").feature("vol1").set("colortable", "Dipole");
    model.result("pg1").feature("vol1").set("showsolutionparams", "on");
    model.result("pg1").feature("vol1").set("data", "parent");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u7535\u573a (ec)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").feature().create("mslc1", "Multislice");
    model.result("pg2").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg2").feature("mslc1").set("solutionparams", "parent");
    model.result("pg2").feature("mslc1").set("expr", "ec.normE");
    model.result("pg2").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg2").feature("mslc1").set("xcoord", "ec.CPx");
    model.result("pg2").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg2").feature("mslc1").set("ycoord", "ec.CPy");
    model.result("pg2").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg2").feature("mslc1").set("zcoord", "ec.CPz");
    model.result("pg2").feature("mslc1").set("colortable", "Prism");
    model.result("pg2").feature("mslc1").set("colortabletrans", "nonlinear");
    model.result("pg2").feature("mslc1").set("colorcalibration", -0.8);
    model.result("pg2").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg2").feature("mslc1").set("data", "parent");
    model.result("pg2").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg2").feature("strmsl1").set("expr", new String[]{"ec.Ex", "ec.Ey", "ec.Ez"});
    model.result("pg2").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg2").feature("strmsl1").set("xcoord", "ec.CPx");
    model.result("pg2").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg2").feature("strmsl1").set("ycoord", "ec.CPy");
    model.result("pg2").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg2").feature("strmsl1").set("zcoord", "ec.CPz");
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
    model.result("pg2").feature("strmsl1").feature("col1").set("expr", "ec.normE");
    model.result("pg2").feature("strmsl1").feature("col1").set("colortable", "PrismDark");
    model.result("pg2").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg2").feature("strmsl1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg2").feature("strmsl1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg2").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg2").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").setIndex("looplevel", 11, 0);
    model.result("pg3").label("\u6d53\u5ea6, \u6d41\u7ebf (tds)");
    model.result("pg3").set("titletype", "custom");
    model.result("pg3").set("typeintitle", true);
    model.result("pg3").set("prefixintitle", "");
    model.result("pg3").create("str1", "Streamline");
    model.result("pg3").feature("str1").set("expr", new String[]{"tds.tflux_cx", "tds.tflux_cy", "tds.tflux_cz"});
    model.result("pg3").feature("str1").set("posmethod", "start");
    model.result("pg3").feature("str1").set("pointtype", "arrow");
    model.result("pg3").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg3").feature("str1").set("color", "gray");
    model.result("pg3").feature("str1").create("col", "Color");
    model.result("pg3").feature("str1").feature("col").set("expr", "c");
    model.result("pg3").feature("str1").feature("col").set("colortable", "Prism");
    model.result("pg3").feature("str1").feature("col").set("titletype", "custom");
    model.result("pg3").feature("str1").set("linetype", "ribbon");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").setIndex("looplevel", 11, 0);
    model.result("pg4").label("\u6d53\u5ea6, \u8868\u9762 (tds)");
    model.result("pg4").set("titletype", "custom");
    model.result("pg4").set("prefixintitle", "");
    model.result("pg4").set("expressionintitle", false);
    model.result("pg4").set("typeintitle", false);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"c"});
    model.result("pg4").feature("surf1").set("colortable", "Prism");
    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").label("\u901f\u5ea6 (spf)");
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").feature().create("slc1", "Slice");
    model.result("pg5").feature("slc1").label("\u5207\u9762");
    model.result("pg5").feature("slc1").set("showsolutionparams", "on");
    model.result("pg5").feature("slc1").set("expr", "spf.U");
    model.result("pg5").feature("slc1").set("smooth", "internal");
    model.result("pg5").feature("slc1").set("showsolutionparams", "on");
    model.result("pg5").feature("slc1").set("data", "parent");
    model.result().dataset().create("surf1", "Surface");
    model.result().dataset("surf1").label("\u5916\u58c1");
    model.result().dataset("surf1").set("data", "dset1");
    model.result().dataset("surf1").selection().geom("geom1", 2);
    model.result().dataset("surf1").selection()
         .set(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 21, 22, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49);
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").label("\u538b\u529b (spf)");
    model.result("pg6").set("data", "surf1");
    model.result("pg6").setIndex("looplevel", 11, 0);
    model.result("pg6").set("frametype", "spatial");
    model.result("pg6").feature().create("surf1", "Surface");
    model.result("pg6").feature("surf1").label("\u8868\u9762");
    model.result("pg6").feature("surf1").set("showsolutionparams", "on");
    model.result("pg6").feature("surf1").set("expr", "p");
    model.result("pg6").feature("surf1").set("colortable", "Dipole");
    model.result("pg6").feature("surf1").set("smooth", "internal");
    model.result("pg6").feature("surf1").set("showsolutionparams", "on");
    model.result("pg6").feature("surf1").set("data", "parent");
    model.result("pg6").feature("surf1").feature().create("tran1", "Transparency");
    model.result("pg1").run();
    model.result("pg4").run();
    model.result("pg4").label("\u6d53\u5ea6 - \u6a21\u5f0f A");
    model.result("pg4").setIndex("looplevel", 1, 0);
    model.result("pg4").set("titletype", "none");
    model.result("pg4").set("showlegendsunit", true);
    model.result("pg4").run();
    model.result("pg4").set("showlegends", false);
    model.result("pg4").setIndex("looplevel", 2, 0);
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 3, 0);
    model.result("pg4").run();
    model.result().dataset().create("cln1", "CutLine3D");
    model.result().dataset("cln1").setIndex("genpoints", 10, 0, 0);
    model.result().dataset("cln1").setIndex("genpoints", 10, 0, 1);
    model.result().dataset("cln1").setIndex("genpoints", 20, 0, 2);
    model.result().dataset("cln1").setIndex("genpoints", 10, 1, 0);
    model.result().dataset("cln1").setIndex("genpoints", 10, 1, 1);
    model.result().dataset("cln1").setIndex("genpoints", -200, 1, 2);
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").label("\u6d53\u5ea6\u7ebf\u56fe - \u6a21\u5f0f A");
    model.result("pg7").set("data", "cln1");
    model.result("pg7").set("titletype", "none");
    model.result("pg7").set("legendlayout", "outside");
    model.result("pg7").create("lngr1", "LineGraph");
    model.result("pg7").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg7").feature("lngr1").set("linewidth", "preference");
    model.result("pg7").feature("lngr1").set("expr", "c");
    model.result("pg7").feature("lngr1").set("descr", "\u6469\u5c14\u6d53\u5ea6\uff0cc");
    model.result("pg7").feature("lngr1").set("xdata", "expr");
    model.result("pg7").feature("lngr1").set("xdataexpr", "10[um]-z");
    model.result("pg7").feature("lngr1").set("colorcycle", "long");
    model.result("pg7").feature("lngr1").set("legend", true);
    model.result("pg7").run();

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/ec", true);
    model.study("std2").feature("stat").setSolveFor("/physics/tds", true);
    model.study("std2").feature("stat").setSolveFor("/physics/spf", true);
    model.study("std2").label("\u7814\u7a76 - \u6a21\u5f0f B");
    model.study("std2").feature("stat").setSolveFor("/physics/ec", false);
    model.study("std2").feature("stat").setSolveFor("/physics/tds", false);
    model.study("std2").feature("stat").label("\u7a33\u6001 - \u805a\u7126\u9636\u6bb5");
    model.study("std2").create("stat2", "Stationary");
    model.study("std2").feature("stat2").label("\u7a33\u6001 2 - \u805a\u7126\u9636\u6bb5");
    model.study("std2").feature("stat2").set("useadvanceddisable", true);
    model.study("std2").feature("stat2").set("disabledphysics", new String[]{"ec/pot2", "ec/gnd2"});
    model.study("std2").feature("stat2").setSolveFor("/physics/tds", false);
    model.study("std2").feature("stat2").setSolveFor("/physics/spf", false);
    model.study("std2").create("stat3", "Stationary");
    model.study("std2").feature("stat3").label("\u7a33\u6001 3 - \u805a\u7126\u9636\u6bb5");
    model.study("std2").feature("stat3").set("useadvanceddisable", true);
    model.study("std2").feature("stat3").setSolveFor("/physics/ec", false);
    model.study("std2").feature("stat3").set("disabledphysics", new String[]{"tds/cdm2", "tds/fl1"});
    model.study("std2").feature("stat3").setSolveFor("/physics/spf", false);
    model.study("std2").create("stat4", "Stationary");
    model.study("std2").feature("stat4").label("\u7a33\u6001 - \u6ce8\u5c04\u9636\u6bb5");
    model.study("std2").feature("stat4").setSolveFor("/physics/tds", false);
    model.study("std2").feature("stat4").setSolveFor("/physics/spf", false);
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").label("\u77ac\u6001 - \u6ce8\u5c04\u9636\u6bb5");
    model.study("std2").feature("time").set("tlist", "range(0,0.06,0.6)");
    model.study("std2").feature("time").setSolveFor("/physics/ec", false);
    model.study("std2").feature("time").setSolveFor("/physics/spf", false);
    model.study("std2").createAutoSequences("all");

    model.sol("sol6").runAll();

    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").label("\u7535\u52bf (ec) 1");
    model.result("pg8").set("data", "dset6");
    model.result("pg8").setIndex("looplevel", 11, 0);
    model.result("pg8").set("frametype", "spatial");
    model.result("pg8").set("showlegendsmaxmin", true);
    model.result("pg8").feature().create("vol1", "Volume");
    model.result("pg8").feature("vol1").set("showsolutionparams", "on");
    model.result("pg8").feature("vol1").set("solutionparams", "parent");
    model.result("pg8").feature("vol1").set("colortable", "Dipole");
    model.result("pg8").feature("vol1").set("showsolutionparams", "on");
    model.result("pg8").feature("vol1").set("data", "parent");
    model.result().create("pg9", "PlotGroup3D");
    model.result("pg9").label("\u7535\u573a (ec) 1");
    model.result("pg9").set("data", "dset6");
    model.result("pg9").setIndex("looplevel", 11, 0);
    model.result("pg9").set("frametype", "spatial");
    model.result("pg9").set("showlegendsmaxmin", true);
    model.result("pg9").feature().create("mslc1", "Multislice");
    model.result("pg9").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg9").feature("mslc1").set("solutionparams", "parent");
    model.result("pg9").feature("mslc1").set("expr", "ec.normE");
    model.result("pg9").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg9").feature("mslc1").set("xcoord", "ec.CPx");
    model.result("pg9").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg9").feature("mslc1").set("ycoord", "ec.CPy");
    model.result("pg9").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg9").feature("mslc1").set("zcoord", "ec.CPz");
    model.result("pg9").feature("mslc1").set("colortable", "Prism");
    model.result("pg9").feature("mslc1").set("colortabletrans", "nonlinear");
    model.result("pg9").feature("mslc1").set("colorcalibration", -0.8);
    model.result("pg9").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg9").feature("mslc1").set("data", "parent");
    model.result("pg9").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg9").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg9").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg9").feature("strmsl1").set("expr", new String[]{"ec.Ex", "ec.Ey", "ec.Ez"});
    model.result("pg9").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg9").feature("strmsl1").set("xcoord", "ec.CPx");
    model.result("pg9").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg9").feature("strmsl1").set("ycoord", "ec.CPy");
    model.result("pg9").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg9").feature("strmsl1").set("zcoord", "ec.CPz");
    model.result("pg9").feature("strmsl1").set("titletype", "none");
    model.result("pg9").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg9").feature("strmsl1").set("udist", 0.02);
    model.result("pg9").feature("strmsl1").set("maxlen", 0.4);
    model.result("pg9").feature("strmsl1").set("maxsteps", 5000);
    model.result("pg9").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg9").feature("strmsl1").set("inheritcolor", false);
    model.result("pg9").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg9").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg9").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg9").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg9").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg9").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg9").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg9").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg9").feature("strmsl1").set("data", "parent");
    model.result("pg9").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg9").feature("strmsl1").feature().create("col1", "Color");
    model.result("pg9").feature("strmsl1").feature("col1").set("expr", "ec.normE");
    model.result("pg9").feature("strmsl1").feature("col1").set("colortable", "PrismDark");
    model.result("pg9").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg9").feature("strmsl1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg9").feature("strmsl1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg9").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg9").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg10", "PlotGroup3D");
    model.result("pg10").set("data", "dset6");
    model.result("pg10").setIndex("looplevel", 11, 0);
    model.result("pg10").label("\u6d53\u5ea6, \u6d41\u7ebf (tds) 1");
    model.result("pg10").set("titletype", "custom");
    model.result("pg10").set("typeintitle", true);
    model.result("pg10").set("prefixintitle", "");
    model.result("pg10").create("str1", "Streamline");
    model.result("pg10").feature("str1").set("expr", new String[]{"tds.tflux_cx", "tds.tflux_cy", "tds.tflux_cz"});
    model.result("pg10").feature("str1").set("posmethod", "start");
    model.result("pg10").feature("str1").set("pointtype", "arrow");
    model.result("pg10").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg10").feature("str1").set("color", "gray");
    model.result("pg10").feature("str1").create("col", "Color");
    model.result("pg10").feature("str1").feature("col").set("expr", "c");
    model.result("pg10").feature("str1").feature("col").set("colortable", "Prism");
    model.result("pg10").feature("str1").feature("col").set("titletype", "custom");
    model.result("pg10").feature("str1").set("linetype", "ribbon");
    model.result().create("pg11", "PlotGroup3D");
    model.result("pg11").set("data", "dset6");
    model.result("pg11").setIndex("looplevel", 11, 0);
    model.result("pg11").label("\u6d53\u5ea6, \u8868\u9762 (tds)");
    model.result("pg11").set("titletype", "custom");
    model.result("pg11").set("prefixintitle", "");
    model.result("pg11").set("expressionintitle", false);
    model.result("pg11").set("typeintitle", false);
    model.result("pg11").create("surf1", "Surface");
    model.result("pg11").feature("surf1").set("expr", new String[]{"c"});
    model.result("pg11").feature("surf1").set("colortable", "Prism");
    model.result().dataset("dset6").set("geom", "geom1");
    model.result().create("pg12", "PlotGroup3D");
    model.result("pg12").label("\u901f\u5ea6 (spf) 1");
    model.result("pg12").set("data", "dset6");
    model.result("pg12").setIndex("looplevel", 11, 0);
    model.result("pg12").set("frametype", "spatial");
    model.result("pg12").feature().create("slc1", "Slice");
    model.result("pg12").feature("slc1").label("\u5207\u9762");
    model.result("pg12").feature("slc1").set("showsolutionparams", "on");
    model.result("pg12").feature("slc1").set("expr", "spf.U");
    model.result("pg12").feature("slc1").set("smooth", "internal");
    model.result("pg12").feature("slc1").set("showsolutionparams", "on");
    model.result("pg12").feature("slc1").set("data", "parent");
    model.result().dataset().create("surf2", "Surface");
    model.result().dataset("surf2").label("\u5916\u58c1 1");
    model.result().dataset("surf2").set("data", "dset6");

    return model;
  }

  public static Model run2(Model model) {
    model.result().dataset("surf2").selection().geom("geom1", 2);
    model.result().dataset("surf2").selection()
         .set(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 21, 22, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49);
    model.result().create("pg13", "PlotGroup3D");
    model.result("pg13").label("\u538b\u529b (spf) 1");
    model.result("pg13").set("data", "surf2");
    model.result("pg13").setIndex("looplevel", 11, 0);
    model.result("pg13").set("frametype", "spatial");
    model.result("pg13").feature().create("surf1", "Surface");
    model.result("pg13").feature("surf1").label("\u8868\u9762");
    model.result("pg13").feature("surf1").set("showsolutionparams", "on");
    model.result("pg13").feature("surf1").set("expr", "p");
    model.result("pg13").feature("surf1").set("colortable", "Dipole");
    model.result("pg13").feature("surf1").set("smooth", "internal");
    model.result("pg13").feature("surf1").set("showsolutionparams", "on");
    model.result("pg13").feature("surf1").set("data", "parent");
    model.result("pg13").feature("surf1").feature().create("tran1", "Transparency");
    model.result("pg8").run();
    model.result("pg11").run();
    model.result("pg11").label("\u6d53\u5ea6 - \u6a21\u5f0f B");
    model.result("pg11").setIndex("looplevel", 2, 0);
    model.result("pg11").set("titletype", "none");
    model.result("pg11").set("showlegendsunit", true);

    model.component("comp1").view("view1").set("showgrid", false);
    model.component("comp1").view("view1").set("showaxisorientation", false);

    model.result("pg11").run();
    model.result("pg11").setIndex("looplevel", 3, 0);
    model.result("pg11").run();
    model.result().dataset().create("cln2", "CutLine3D");
    model.result().dataset("cln2").set("data", "dset6");
    model.result().dataset("cln2").setIndex("genpoints", 10, 0, 0);
    model.result().dataset("cln2").setIndex("genpoints", 10, 0, 1);
    model.result().dataset("cln2").setIndex("genpoints", 20, 0, 2);
    model.result().dataset("cln2").setIndex("genpoints", 10, 1, 0);
    model.result().dataset("cln2").setIndex("genpoints", 10, 1, 1);
    model.result().dataset("cln2").setIndex("genpoints", -200, 1, 2);
    model.result().create("pg14", "PlotGroup1D");
    model.result("pg14").run();
    model.result("pg14").label("\u6d53\u5ea6\u7ebf\u56fe - \u6a21\u5f0f B");
    model.result("pg14").set("data", "cln2");
    model.result("pg14").set("titletype", "none");
    model.result("pg14").set("legendlayout", "outside");
    model.result("pg14").create("lngr1", "LineGraph");
    model.result("pg14").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg14").feature("lngr1").set("linewidth", "preference");
    model.result("pg14").feature("lngr1").set("expr", "c");
    model.result("pg14").feature("lngr1").set("descr", "\u6469\u5c14\u6d53\u5ea6\uff0cc");
    model.result("pg14").feature("lngr1").set("xdata", "expr");
    model.result("pg14").feature("lngr1").set("xdataexpr", "10[um]-z");
    model.result("pg14").feature("lngr1").set("colorcycle", "long");
    model.result("pg14").feature("lngr1").set("legend", true);
    model.result("pg14").run();
    model.result("pg1").run();
    model.result().remove("pg1");
    model.result("pg2").run();
    model.result().remove("pg2");
    model.result("pg3").run();
    model.result().remove("pg3");
    model.result("pg4").run();
    model.result("pg5").run();
    model.result().remove("pg5");
    model.result("pg6").run();
    model.result().remove("pg6");
    model.result("pg7").run();
    model.result("pg8").run();
    model.result().remove("pg8");
    model.result("pg9").run();
    model.result().remove("pg9");
    model.result("pg10").run();
    model.result().remove("pg10");
    model.result("pg11").run();
    model.result("pg12").run();
    model.result().remove("pg12");
    model.result("pg13").run();
    model.result().remove("pg13");
    model.result("pg14").run();
    model.result("pg4").run();

    model.title("\u7535\u52a8\u9600\u4e2d\u7684\u4f20\u9012");

    model
         .description("\u672c\u4f8b\u63cf\u8ff0\u4e00\u4e2a\u5fae\u6d41\u9053\u7cfb\u7edf\u4e2d\u7684\u538b\u529b\u9a71\u52a8\u6d41\u548c\u7535\u6cf3\u3002\u6837\u54c1\u548c\u7f13\u51b2\u6eb6\u6db2\u5728\u538b\u529b\u9a71\u52a8\u4e0b\u53d1\u751f\u805a\u96c6\uff0c\u4f7f\u6837\u54c1\u5728\u805a\u96c6\u6d41\u9053\u4e2d\u7684\u5206\u5e03\u53d7\u9650\u5236\u3002\u5f53\u8fbe\u5230\u7a33\u5b9a\u72b6\u6001\u540e\uff0c\u5173\u95ed\u538b\u529b\u9a71\u52a8\u6d41\uff0c\u65bd\u52a0\u4e00\u4e2a\u7535\u573a\uff0c\u4f7f\u5206\u79bb\u7684\u6837\u54c1\u79bb\u5b50\u6d41\u5165\u6ce8\u5c04\u6d41\u9053\u3002");

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

    model.label("electrokinetic_valve.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
