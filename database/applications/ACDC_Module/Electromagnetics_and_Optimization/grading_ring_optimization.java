/*
 * grading_ring_optimization.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:23 by COMSOL 6.3.0.290. */
public class grading_ring_optimization {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Electromagnetics_and_Optimization");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("es", "Electrostatics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/es", true);

    model.param().set("Vi", "500[kV]");
    model.param().descr("Vi", "Overvoltage amplitude");
    model.param().set("H", "30[mm]");
    model.param().descr("H", "Thickness of the grading ring");
    model.param().set("D", "350[mm]");
    model.param().descr("D", "Diameter of the grading ring");
    model.param().set("d", "150[mm]");
    model.param().descr("d", "Distance of the grading ring from the end");

    model.component("comp1").geom("geom1").insertFile("high_voltage_insulator_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").physics("es").create("ccns1", "ChargeConservationSolid", 2);
    model.component("comp1").physics("es").feature("ccns1").selection().set(3, 4, 5, 7);
    model.component("comp1").physics("es").create("term1", "DomainTerminal", 2);
    model.component("comp1").physics("es").feature("term1").label("Metal Fitting, Line End");
    model.component("comp1").physics("es").feature("term1").selection().set(2);
    model.component("comp1").physics("es").feature("term1").set("TerminalType", "Voltage");
    model.component("comp1").physics("es").feature("term1").set("V0", "Vi");
    model.component("comp1").physics("es").create("term2", "DomainTerminal", 2);
    model.component("comp1").physics("es").feature("term2").label("Metal Fitting, Ground End");
    model.component("comp1").physics("es").feature("term2").selection().set(6);
    model.component("comp1").physics("es").feature("term2").set("TerminalType", "Voltage");
    model.component("comp1").physics("es").feature("term2").set("V0", 0);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("Core Rod");
    model.component("comp1").material("mat1").selection().set(3, 4, 5);
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermittivity", new String[]{"5"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("Weather Shed");
    model.component("comp1").material("mat2").selection().set(7);
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermittivity", new String[]{"3"});

    model.study("std1").label("Study 1, Without Grading Rings");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("Electric Potential (es)");
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
    model.result("pg1").feature("str1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94);
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
    model.result().dataset("rev1").set("data", "dset1");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("Electric Potential, Revolved Geometry (es)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").feature().create("vol1", "Volume");
    model.result("pg2").feature("vol1").set("showsolutionparams", "on");
    model.result("pg2").feature("vol1").set("solutionparams", "parent");
    model.result("pg2").feature("vol1").set("colortable", "Dipole");
    model.result("pg2").feature("vol1").set("showsolutionparams", "on");
    model.result("pg2").feature("vol1").set("data", "parent");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("Electric Field (es)");
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
    model.result("pg3").feature("str1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94);
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
    model.result().dataset("dset1").selection().geom("geom1", 2);
    model.result().dataset("dset1").selection().geom("geom1", 2);
    model.result().dataset("dset1").selection().set(2, 3, 4, 5, 6, 7);
    model.result().dataset("rev1").set("revangle", 360);
    model.result("pg1").run();
    model.result("pg1").label("Electric Potential, Without Grading Rings");

    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("c2", "Circle");
    model.component("comp1").geom("geom1").feature("c2").set("r", "H/2");
    model.component("comp1").geom("geom1").feature("c2").set("pos", new String[]{"D/2", "d"});
    model.component("comp1").geom("geom1").feature().duplicate("c3", "c2");
    model.component("comp1").geom("geom1").feature("c3").set("pos", new String[]{"D/2", "1150-d"});
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("es").create("term3", "DomainTerminal", 2);
    model.component("comp1").physics("es").feature("term3").label("Grading Ring, Line End");
    model.component("comp1").physics("es").feature("term3").selection().set(8);
    model.component("comp1").physics("es").feature("term3").set("TerminalType", "Voltage");
    model.component("comp1").physics("es").feature("term3").set("V0", "Vi");
    model.component("comp1").physics("es").create("term4", "DomainTerminal", 2);
    model.component("comp1").physics("es").feature("term4").label("Grading Ring, Ground End");
    model.component("comp1").physics("es").feature("term4").selection().set(9);
    model.component("comp1").physics("es").feature("term4").set("TerminalType", "Voltage");
    model.component("comp1").physics("es").feature("term4").set("V0", 0);

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/es", true);
    model.study("std2").label("Study 2, With Grading Rings");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").label("Electric Potential (es)");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").set("dataisaxisym", "off");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").set("showlegendsmaxmin", true);
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("solutionparams", "parent");
    model.result("pg4").feature("surf1").set("colortable", "Dipole");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result("pg4").feature().create("str1", "Streamline");
    model.result("pg4").feature("str1").set("showsolutionparams", "on");
    model.result("pg4").feature("str1").set("solutionparams", "parent");
    model.result("pg4").feature("str1").set("titletype", "none");
    model.result("pg4").feature("str1").set("posmethod", "uniform");
    model.result("pg4").feature("str1").set("udist", 0.02);
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
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102);
    model.result("pg4").feature("str1").set("inheritplot", "surf1");
    model.result("pg4").feature("str1").feature().create("col1", "Color");
    model.result("pg4").feature("str1").feature("col1").set("colortable", "DipoleDark");
    model.result("pg4").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg4").feature("str1").feature().create("filt1", "Filter");
    model.result("pg4").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().dataset().create("rev2", "Revolve2D");
    model.result().dataset("rev2").set("data", "none");
    model.result().dataset("rev2").set("startangle", -90);
    model.result().dataset("rev2").set("revangle", 225);
    model.result().dataset("rev2").set("data", "dset2");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").label("Electric Potential, Revolved Geometry (es) 1");
    model.result("pg5").set("data", "rev2");
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").set("showlegendsmaxmin", true);
    model.result("pg5").feature().create("vol1", "Volume");
    model.result("pg5").feature("vol1").set("showsolutionparams", "on");
    model.result("pg5").feature("vol1").set("solutionparams", "parent");
    model.result("pg5").feature("vol1").set("colortable", "Dipole");
    model.result("pg5").feature("vol1").set("showsolutionparams", "on");
    model.result("pg5").feature("vol1").set("data", "parent");
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").label("Electric Field (es) 1");
    model.result("pg6").set("data", "dset2");
    model.result("pg6").set("dataisaxisym", "off");
    model.result("pg6").set("frametype", "spatial");
    model.result("pg6").set("showlegendsmaxmin", true);
    model.result("pg6").feature().create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("showsolutionparams", "on");
    model.result("pg6").feature("surf1").set("solutionparams", "parent");
    model.result("pg6").feature("surf1").set("expr", "es.normE");
    model.result("pg6").feature("surf1").set("colortable", "Prism");
    model.result("pg6").feature("surf1").set("colortabletrans", "nonlinear");
    model.result("pg6").feature("surf1").set("colorcalibration", -0.8);
    model.result("pg6").feature("surf1").set("showsolutionparams", "on");
    model.result("pg6").feature("surf1").set("data", "parent");
    model.result("pg6").feature().create("str1", "Streamline");
    model.result("pg6").feature("str1").set("showsolutionparams", "on");
    model.result("pg6").feature("str1").set("solutionparams", "parent");
    model.result("pg6").feature("str1").set("titletype", "none");
    model.result("pg6").feature("str1").set("posmethod", "uniform");
    model.result("pg6").feature("str1").set("udist", 0.02);
    model.result("pg6").feature("str1").set("maxlen", 0.4);
    model.result("pg6").feature("str1").set("maxsteps", 5000);
    model.result("pg6").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg6").feature("str1").set("inheritcolor", false);
    model.result("pg6").feature("str1").set("showsolutionparams", "on");
    model.result("pg6").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg6").feature("str1").set("showsolutionparams", "on");
    model.result("pg6").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg6").feature("str1").set("showsolutionparams", "on");
    model.result("pg6").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg6").feature("str1").set("showsolutionparams", "on");
    model.result("pg6").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg6").feature("str1").set("data", "parent");
    model.result("pg6").feature("str1").selection().geom("geom1", 1);
    model.result("pg6").feature("str1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102);
    model.result("pg6").feature("str1").set("inheritplot", "surf1");
    model.result("pg6").feature("str1").feature().create("col1", "Color");
    model.result("pg6").feature("str1").feature("col1").set("expr", "es.normE");
    model.result("pg6").feature("str1").feature("col1").set("colortable", "PrismDark");
    model.result("pg6").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg6").feature("str1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg6").feature("str1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg6").feature("str1").feature().create("filt1", "Filter");
    model.result("pg6").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg4").run();
    model.result().dataset("dset2").selection().geom("geom1", 2);
    model.result().dataset("dset2").selection().geom("geom1", 2);
    model.result().dataset("dset2").selection().set(2, 3, 4, 5, 6, 7, 8, 9);
    model.result().dataset("rev2").set("revangle", 360);
    model.result("pg4").run();
    model.result("pg4").label("Electric Potential, With Grading Rings");
    model.result("pg5").run();
    model.result("pg5").set("edges", false);
    model.result("pg5").run();

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("Shed Surface");
    model.component("comp1").selection("sel1").geom(1);
    model.component("comp1").selection("sel1")
         .set(22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 57, 58, 59, 60, 61, 62, 78, 79, 80, 81, 82, 83);

    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").label("Tangential Electric Field, z-Component");
    model.result("pg7").create("lngr1", "LineGraph");
    model.result("pg7").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg7").feature("lngr1").set("linewidth", "preference");
    model.result("pg7").feature("lngr1").label("Without Grading Rings");
    model.result("pg7").feature("lngr1").set("data", "dset1");
    model.result("pg7").feature("lngr1").selection().named("sel1");
    model.result("pg7").feature("lngr1").set("expr", "es.tEz");
    model.result("pg7").feature("lngr1").set("unit", "kV/cm");
    model.result("pg7").feature("lngr1").set("xdata", "expr");
    model.result("pg7").feature("lngr1").set("xdataexpr", "z");
    model.result("pg7").feature("lngr1").set("legend", true);
    model.result("pg7").feature("lngr1").set("autoplotlabel", true);
    model.result("pg7").feature("lngr1").set("autosolution", false);
    model.result("pg7").feature().duplicate("lngr2", "lngr1");
    model.result("pg7").run();
    model.result("pg7").feature("lngr2").label("With Grading Rings");
    model.result("pg7").feature("lngr2").set("data", "dset2");
    model.result("pg7").feature("lngr2").set("titletype", "none");
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg1").run();
    model.result("pg1").feature("str1").set("udist", 0.005);
    model.result("pg1").run();
    model.result("pg3").run();
    model.result("pg3").feature("str1").set("udist", 0.005);
    model.result("pg3").run();
    model.result("pg4").run();
    model.result("pg4").feature("str1").set("udist", 0.005);
    model.result("pg4").run();
    model.result("pg6").run();
    model.result("pg6").feature("str1").set("udist", 0.005);
    model.result("pg6").run();
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").set("edges", false);
    model.result("pg2").run();
    model.result("pg5").run();

    model.title("\u9ad8\u538b\u7edd\u7f18\u5b50");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u4f7f\u7528\u201c\u9759\u7535\u201d\u63a5\u53e3\u6765\u8ba1\u7b97\u5178\u578b\u9ad8\u538b\u7edd\u7f18\u5b50\u7684\u8868\u9762\u7535\u573a\u3002\u7ed3\u679c\u8868\u660e\uff0c\u901a\u8fc7\u5b89\u88c5\u5747\u538b\u73af\uff0c\u53ef\u4ee5\u5927\u5e45\u51cf\u5c0f\u8868\u9762\u7535\u573a\u7684\u4e0d\u5747\u5300\u6027\u548c\u6700\u5927\u503c\u3002\u8fd9\u6837\uff0c\u9ad8\u538b\u7edd\u7f18\u5b50\u4fbf\u80fd\u627f\u53d7\u8f83\u9ad8\u7684\u95ea\u7edc\u7535\u538b\u3002");

    model.label("high_voltage_insulator.mph");

    model.result("pg5").run();
    model.result("pg7").run();

    model.study().create("std3");
    model.study("std3").create("stat", "Stationary");
    model.study("std3").feature("stat").setSolveFor("/physics/es", true);
    model.study("std3").create("opt", "Optimization");

    model.component("comp1").probe().create("bnd1", "Boundary");
    model.component("comp1").probe("bnd1").set("intsurface", true);
    model.component("comp1").probe("bnd1").set("type", "maximum");
    model.component("comp1").probe("bnd1").selection().named("sel1");
    model.component("comp1").probe("bnd1").set("expr", "es.tEz");
    model.component("comp1").probe("bnd1").set("unit", "kV/cm");

    model.study("std3").feature("opt").set("optobj", new String[]{"comp1.bnd1"});
    model.study("std3").feature("opt").set("descr", new String[]{"\u8fb9\u754c\u63a2\u9488 1"});
    model.study("std3").feature("opt").setIndex("pname", "Vi", 0);
    model.study("std3").feature("opt").setIndex("initval", "500[kV]", 0);
    model.study("std3").feature("opt").setIndex("scale", 1, 0);
    model.study("std3").feature("opt").setIndex("lbound", "", 0);
    model.study("std3").feature("opt").setIndex("ubound", "", 0);
    model.study("std3").feature("opt").setIndex("pname", "Vi", 0);
    model.study("std3").feature("opt").setIndex("initval", "500[kV]", 0);
    model.study("std3").feature("opt").setIndex("scale", 1, 0);
    model.study("std3").feature("opt").setIndex("lbound", "", 0);
    model.study("std3").feature("opt").setIndex("ubound", "", 0);
    model.study("std3").feature("opt").setIndex("pname", "H", 1);
    model.study("std3").feature("opt").setIndex("initval", "30[mm]", 1);
    model.study("std3").feature("opt").setIndex("scale", 1, 1);
    model.study("std3").feature("opt").setIndex("lbound", "", 1);
    model.study("std3").feature("opt").setIndex("ubound", "", 1);
    model.study("std3").feature("opt").setIndex("pname", "H", 1);
    model.study("std3").feature("opt").setIndex("initval", "30[mm]", 1);
    model.study("std3").feature("opt").setIndex("scale", 1, 1);
    model.study("std3").feature("opt").setIndex("lbound", "", 1);
    model.study("std3").feature("opt").setIndex("ubound", "", 1);
    model.study("std3").feature("opt").setIndex("pname", "D", 0);
    model.study("std3").feature("opt").setIndex("scale", "1[m]", 0);
    model.study("std3").feature("opt").setIndex("lbound", "200[mm]", 0);
    model.study("std3").feature("opt").setIndex("ubound", "1000[mm]", 0);
    model.study("std3").feature("opt").setIndex("pname", "d", 1);
    model.study("std3").feature("opt").setIndex("scale", "1[m]", 1);
    model.study("std3").feature("opt").setIndex("lbound", "10[mm]", 1);
    model.study("std3").feature("opt").setIndex("ubound", "500[mm]", 1);
    model.study("std3").createAutoSequences("all");

    model.sol().create("sol4");
    model.sol("sol4").study("std3");
    model.sol("sol4").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol4");

    model.component("comp1").probe("bnd1").genResult("none");

    model.batch("o1").run("compute");

    model.result().create("pg9", "PlotGroup2D");
    model.result("pg9").label("\u7535\u52bf (es)");
    model.result("pg9").set("data", "dset4");
    model.result("pg9").setIndex("looplevel", 1, 0);
    model.result("pg9").set("dataisaxisym", "off");
    model.result("pg9").set("frametype", "spatial");
    model.result("pg9").set("showlegendsmaxmin", true);
    model.result("pg9").feature().create("surf1", "Surface");
    model.result("pg9").feature("surf1").set("showsolutionparams", "on");
    model.result("pg9").feature("surf1").set("solutionparams", "parent");
    model.result("pg9").feature("surf1").set("colortable", "Dipole");
    model.result("pg9").feature("surf1").set("showsolutionparams", "on");
    model.result("pg9").feature("surf1").set("data", "parent");
    model.result("pg9").feature().create("str1", "Streamline");
    model.result("pg9").feature("str1").set("showsolutionparams", "on");
    model.result("pg9").feature("str1").set("solutionparams", "parent");
    model.result("pg9").feature("str1").set("titletype", "none");
    model.result("pg9").feature("str1").set("posmethod", "uniform");
    model.result("pg9").feature("str1").set("udist", 0.02);
    model.result("pg9").feature("str1").set("maxlen", 0.4);
    model.result("pg9").feature("str1").set("maxsteps", 5000);
    model.result("pg9").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg9").feature("str1").set("inheritcolor", false);
    model.result("pg9").feature("str1").set("showsolutionparams", "on");
    model.result("pg9").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg9").feature("str1").set("showsolutionparams", "on");
    model.result("pg9").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg9").feature("str1").set("showsolutionparams", "on");
    model.result("pg9").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg9").feature("str1").set("showsolutionparams", "on");
    model.result("pg9").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg9").feature("str1").set("data", "parent");
    model.result("pg9").feature("str1").selection().geom("geom1", 1);
    model.result("pg9").feature("str1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102);
    model.result("pg9").feature("str1").set("inheritplot", "surf1");
    model.result("pg9").feature("str1").feature().create("col1", "Color");
    model.result("pg9").feature("str1").feature("col1").set("colortable", "DipoleDark");
    model.result("pg9").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg9").feature("str1").feature().create("filt1", "Filter");
    model.result("pg9").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().dataset().create("rev3", "Revolve2D");
    model.result().dataset("rev3").set("data", "none");
    model.result().dataset("rev3").set("startangle", -90);
    model.result().dataset("rev3").set("revangle", 225);
    model.result().dataset("rev3").set("data", "dset4");
    model.result().create("pg10", "PlotGroup3D");
    model.result("pg10").label("\u7535\u52bf\uff0c\u56de\u8f6c\u51e0\u4f55 (es)");
    model.result("pg10").set("data", "rev3");
    model.result("pg10").setIndex("looplevel", 1, 0);
    model.result("pg10").set("frametype", "spatial");
    model.result("pg10").set("showlegendsmaxmin", true);
    model.result("pg10").feature().create("vol1", "Volume");
    model.result("pg10").feature("vol1").set("showsolutionparams", "on");
    model.result("pg10").feature("vol1").set("solutionparams", "parent");
    model.result("pg10").feature("vol1").set("colortable", "Dipole");
    model.result("pg10").feature("vol1").set("showsolutionparams", "on");
    model.result("pg10").feature("vol1").set("data", "parent");
    model.result().create("pg11", "PlotGroup2D");
    model.result("pg11").label("\u7535\u573a (es)");
    model.result("pg11").set("data", "dset4");
    model.result("pg11").setIndex("looplevel", 1, 0);
    model.result("pg11").set("dataisaxisym", "off");
    model.result("pg11").set("frametype", "spatial");
    model.result("pg11").set("showlegendsmaxmin", true);
    model.result("pg11").feature().create("surf1", "Surface");
    model.result("pg11").feature("surf1").set("showsolutionparams", "on");
    model.result("pg11").feature("surf1").set("solutionparams", "parent");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg11").feature("surf1").set("expr", "es.normE");
    model.result("pg11").feature("surf1").set("colortable", "Prism");
    model.result("pg11").feature("surf1").set("colortabletrans", "nonlinear");
    model.result("pg11").feature("surf1").set("colorcalibration", -0.8);
    model.result("pg11").feature("surf1").set("showsolutionparams", "on");
    model.result("pg11").feature("surf1").set("data", "parent");
    model.result("pg11").feature().create("str1", "Streamline");
    model.result("pg11").feature("str1").set("showsolutionparams", "on");
    model.result("pg11").feature("str1").set("solutionparams", "parent");
    model.result("pg11").feature("str1").set("titletype", "none");
    model.result("pg11").feature("str1").set("posmethod", "uniform");
    model.result("pg11").feature("str1").set("udist", 0.02);
    model.result("pg11").feature("str1").set("maxlen", 0.4);
    model.result("pg11").feature("str1").set("maxsteps", 5000);
    model.result("pg11").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg11").feature("str1").set("inheritcolor", false);
    model.result("pg11").feature("str1").set("showsolutionparams", "on");
    model.result("pg11").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg11").feature("str1").set("showsolutionparams", "on");
    model.result("pg11").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg11").feature("str1").set("showsolutionparams", "on");
    model.result("pg11").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg11").feature("str1").set("showsolutionparams", "on");
    model.result("pg11").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg11").feature("str1").set("data", "parent");
    model.result("pg11").feature("str1").selection().geom("geom1", 1);
    model.result("pg11").feature("str1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102);
    model.result("pg11").feature("str1").set("inheritplot", "surf1");
    model.result("pg11").feature("str1").feature().create("col1", "Color");
    model.result("pg11").feature("str1").feature("col1").set("expr", "es.normE");
    model.result("pg11").feature("str1").feature("col1").set("colortable", "PrismDark");
    model.result("pg11").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg11").feature("str1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg11").feature("str1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg11").feature("str1").feature().create("filt1", "Filter");
    model.result("pg11").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg9").run();

    model.study("std3").feature("opt").set("probewindow", "");

    model.result("pg8").set("window", "window1");
    model.result("pg8").run();
    model.result("pg7").run();
    model.result("pg7").feature().duplicate("lngr3", "lngr2");
    model.result("pg7").run();
    model.result("pg7").feature("lngr3").label("\u5e26\u5747\u538b\u73af\uff0c\u4f18\u5316\u540e");
    model.result("pg7").feature("lngr3").set("data", "dset4");
    model.result("pg7").feature("lngr3").setIndex("looplevelinput", "manual", 0);
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg7").run();
    model.result().dataset("dset4").selection().geom("geom1", 2);
    model.result().dataset("dset4").selection().geom("geom1", 2);
    model.result().dataset("dset4").selection().set(2, 3, 4, 5, 6, 7, 8, 9);
    model.result().dataset("rev3").set("revangle", 360);
    model.result("pg9").run();
    model.result("pg9").run();
    model.result("pg9").feature("str1").set("udist", 0.005);
    model.result("pg9").run();
    model.result("pg9").run();
    model.result("pg10").run();
    model.result("pg10").set("edges", false);
    model.result("pg10").run();
    model.result("pg11").run();
    model.result("pg11").run();
    model.result("pg11").feature("str1").set("udist", 0.005);
    model.result("pg11").run();
    model.result("pg11").run();
    model.result("pg7").run();

    model.title("\u5747\u538b\u73af\u7684\u4f4d\u7f6e\u4f18\u5316");

    model
         .description("\u672c\u4f8b\u901a\u8fc7\u6269\u5c55\u201c\u9ad8\u538b\u7edd\u7f18\u5b50\u201d\u6a21\u578b\uff0c\u6f14\u793a\u5982\u4f55\u8bbe\u7f6e\u201c\u4f18\u5316\u201d\u7814\u7a76\u6765\u786e\u5b9a\u5747\u538b\u73af\u7684\u6700\u4f73\u4f4d\u7f6e\uff0c\u4ece\u800c\u83b7\u5f97\u6700\u9ad8\u7684\u95ea\u7edc\u7535\u538b\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();

    model.label("grading_ring_optimization.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
