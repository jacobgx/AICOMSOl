/*
 * cms_tutorial.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:21 by COMSOL 6.3.0.290. */
public class cms_tutorial {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);

    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new double[]{1, 0.15, 0.2});
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("arr1").selection("input").set("blk1");
    model.component("comp1").geom("geom1").feature("arr1").set("fullsize", new int[]{3, 1, 1});
    model.component("comp1").geom("geom1").feature("arr1").set("displ", new int[]{1, 0, 0});
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").feature("fin").set("createpairs", false);
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").physics("solid").feature("lemm1").create("dmp1", "Damping", 3);
    model.component("comp1").physics("solid").feature("lemm1").feature("dmp1").set("DampingType", "ViscousDamping");
    model.component("comp1").physics("solid").feature("lemm1").feature("dmp1").set("etab", "5e7");
    model.component("comp1").physics("solid").feature("lemm1").feature("dmp1").set("etav", "2e7");
    model.component("comp1").physics("solid").create("fix1", "Fixed", 2);
    model.component("comp1").physics("solid").feature("fix1").selection().set(1);
    model.component("comp1").physics("solid").create("att1", "Attachment", 2);
    model.component("comp1").physics("solid").feature("att1").selection().set(6);
    model.component("comp1").physics("solid").feature().duplicate("att2", "att1");
    model.component("comp1").physics("solid").feature("att2").selection().set(7);
    model.component("comp1").physics("solid").create("fxj1", "FixedJoint", -1);
    model.component("comp1").physics("solid").feature("fxj1").set("Source", "att1");
    model.component("comp1").physics("solid").feature("fxj1").set("Destination", "att2");
    model.component("comp1").physics("solid").create("att3", "Attachment", 2);
    model.component("comp1").physics("solid").feature("att3").selection().set(12);
    model.component("comp1").physics("solid").feature().duplicate("att4", "att3");
    model.component("comp1").physics("solid").feature("att4").selection().set(13);
    model.component("comp1").physics("solid").create("fxj2", "FixedJoint", -1);
    model.component("comp1").physics("solid").feature("fxj2").set("Source", "att3");
    model.component("comp1").physics("solid").feature("fxj2").set("Destination", "att4");
    model.component("comp1").physics("solid").create("att5", "Attachment", 2);
    model.component("comp1").physics("solid").feature("att5").selection().set(18);

    model.param().set("load", "0[Pa]");
    model.param().descr("load", "\u8f7d\u8377\u53c2\u6570");

    model.component("comp1").physics("solid").create("bndl1", "BoundaryLoad", 2);
    model.component("comp1").physics("solid").feature("bndl1").selection().set(18);
    model.component("comp1").physics("solid").feature("bndl1")
         .set("forceReferenceArea", new String[]{"0", "0", "load"});
    model.component("comp1").physics("solid").create("bndl2", "BoundaryLoad", 2);
    model.component("comp1").physics("solid").feature("bndl2").selection().set(2, 8, 14);
    model.component("comp1").physics("solid").feature("bndl2")
         .set("forceReferenceArea", new String[]{"0", "1e5", "0"});

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u6750\u6599\uff0c\u7ebf\u6027");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", new String[]{"200e9"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", new String[]{"0.3"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"7850"});
    model.component("comp1").material().duplicate("mat2", "mat1");
    model.component("comp1").material("mat2").selection().set(2);

    model.component("comp1").func().create("step1", "Step");
    model.component("comp1").func("step1").set("from", 0.8);
    model.component("comp1").func("step1").set("to", 1.2);
    model.component("comp1").func("step1").set("smooth", "1e-3");

    model.component("comp1").material("mat2").label("\u6750\u6599\uff0c\u975e\u7ebf\u6027");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", new String[]{"200e9*step1(solid.eXX)"});

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(1, 7, 13);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().all();
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 4);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", 12);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u7814\u7a76\uff0c\u5b8c\u6574\u6a21\u578b");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").set("tlist", "range(0,0.05,1)");
    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "load", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "Pa", 0);
    model.study("std1").feature("stat").setIndex("pname", "load", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "Pa", 0);
    model.study("std1").feature("stat").setIndex("plistarr", 1, 0);
    model.study("std1").feature("stat").setIndex("punit", "MPa", 0);
    model.study("std1").feature("stat").set("pcontinuationmode", "no");
    model.study("std1").feature("time").set("useparam", true);
    model.study("std1").feature("time").setIndex("pname", "load", 0);
    model.study("std1").feature("time").setIndex("plistarr", "", 0);
    model.study("std1").feature("time").setIndex("punit", "Pa", 0);
    model.study("std1").feature("time").setIndex("pname", "load", 0);
    model.study("std1").feature("time").setIndex("plistarr", "", 0);
    model.study("std1").feature("time").setIndex("punit", "Pa", 0);
    model.study("std1").feature("time").setIndex("plistarr", 0, 0);
    model.study("std1").feature("time").setIndex("punit", "", 0);

    model.component("comp1").probe().create("point1", "Point");
    model.component("comp1").probe("point1").selection().set(21);
    model.component("comp1").probe("point1").set("expr", "w");
    model.component("comp1").probe("point1").set("descractive", true);
    model.component("comp1").probe("point1").set("descr", "\u5168\u6a21\u578b");

    model.study("std1").feature("stat").set("probesel", "none");
    model.study("std1").createAutoSequences("all");

    model.component("comp1").probe("point1").genResult("none");

    model.sol("sol1").runAll();

    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 21, 0);
    model.result("pg2").setIndex("looplevel", 1, 1);
    model.result("pg2").label("\u5e94\u529b (solid)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").create("vol1", "Volume");
    model.result("pg2").feature("vol1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg2").feature("vol1").set("threshold", "manual");
    model.result("pg2").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg2").feature("vol1").set("colortable", "Rainbow");
    model.result("pg2").feature("vol1").set("colortabletrans", "none");
    model.result("pg2").feature("vol1").set("colorscalemode", "linear");
    model.result("pg2").feature("vol1").set("resolution", "custom");
    model.result("pg2").feature("vol1").set("refine", 2);
    model.result("pg2").feature("vol1").set("colortable", "Prism");
    model.result("pg2").feature("vol1").create("def", "Deform");
    model.result("pg2").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg2").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg2").run();

    model.component("comp1").physics("solid").create("rfc1", "ReducedFlexibleComponents", 3);
    model.component("comp1").physics("solid").feature("rfc1").selection().set(1, 3);

    model.common().create("grmi1", "GlobalReducedModelInputs", "");
    model.common("grmi1").setIndex("name", "load", 0);

    model.component("comp1").physics("solid").feature("rfc1").set("nModes", 6);
    model.component("comp1").physics("solid").feature("rfc1").runCommand("createCMSModel");

    model.study("std_rfc1_solid").feature("mr_rfc1_solid").feature("time_rfc1_solid")
         .set("disabledphysics", new String[]{"solid/fxj1", "solid/fxj2", "solid/bndl2"});
    model.study("std_rfc1_solid").createAutoSequences("all");

    model.sol().create("sol6");
    model.sol("sol6").study("std_rfc1_solid");
    model.sol("sol6").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol6");
    model.batch("p1").run("compute");

    model.study().create("std2");
    model.study("std2").label("\u7814\u7a76\uff0c\u964d\u9636\u6a21\u578b");
    model.study("std2").feature().copy("stat", "std1/stat");
    model.study("std2").feature().copy("time", "std1/time");
    model.study("std2").feature("stat").set("useadvanceddisable", true);
    model.study("std2").feature("stat").set("disabledphysics", new String[]{"solid/bndl1"});
    model.study("std2").feature("time").set("useadvanceddisable", true);
    model.study("std2").feature("time").set("disabledphysics", new String[]{"solid/bndl1"});

    model.component("comp1").probe().duplicate("point2", "point1");
    model.component("comp1").probe("point2").set("descr", "\u964d\u9636\u6a21\u578b");
    model.component("comp1").probe("point2").set("table", "new");

    model.study("std2").feature("time").set("probesel", "manual");
    model.study("std2").feature("time").set("probes", new String[]{"point2"});
    model.study("std2").createAutoSequences("all");

    model.component("comp1").probe("point2").genResult("none");

    model.sol("sol9").runAll();

    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset11");
    model.result("pg3").setIndex("looplevel", 21, 0);
    model.result("pg3").setIndex("looplevel", 1, 1);
    model.result("pg3").label("\u5e94\u529b (solid) 1");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").create("vol1", "Volume");
    model.result("pg3").feature("vol1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg3").feature("vol1").set("threshold", "manual");
    model.result("pg3").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg3").feature("vol1").set("colortable", "Rainbow");
    model.result("pg3").feature("vol1").set("colortabletrans", "none");
    model.result("pg3").feature("vol1").set("colorscalemode", "linear");
    model.result("pg3").feature("vol1").set("resolution", "custom");
    model.result("pg3").feature("vol1").set("refine", 2);
    model.result("pg3").feature("vol1").set("colortable", "Prism");
    model.result("pg3").feature("vol1").create("def", "Deform");
    model.result("pg3").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg3").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").run();
    model.result("pg4").label("\u53d7\u7ea6\u675f\u7684\u7279\u5f81\u6a21\u6001");
    model.result("pg4").set("data", "dset6");
    model.result("pg4").set("titletype", "none");
    model.result("pg4").set("showlegends", false);
    model.result("pg4").set("plotarrayenable", true);
    model.result("pg4").set("arrayshape", "square");
    model.result("pg4").set("order", "columnmajor");
    model.result("pg4").create("vol1", "Volume");
    model.result("pg4").feature("vol1").set("arraydim", "2");
    model.result("pg4").feature("vol1").set("colortable", "DiscoLight");
    model.result("pg4").feature("vol1").create("def1", "Deform");
    model.result("pg4").run();
    model.result("pg4").feature("vol1").set("arraydim", "2");
    model.result("pg4").run();
    model.result("pg4").feature().duplicate("vol2", "vol1");
    model.result("pg4").feature("vol2").set("arraydim", "2");
    model.result("pg4").run();
    model.result("pg4").feature("vol2").set("data", "dset6");
    model.result("pg4").feature("vol2").set("looplevel", new int[]{2});
    model.result("pg4").feature().duplicate("vol3", "vol2");
    model.result("pg4").feature("vol3").set("arraydim", "2");
    model.result("pg4").run();
    model.result("pg4").feature("vol3").set("looplevel", new int[]{3});
    model.result("pg4").feature().duplicate("vol4", "vol3");
    model.result("pg4").feature("vol4").set("arraydim", "2");
    model.result("pg4").run();
    model.result("pg4").feature("vol4").set("looplevel", new int[]{4});
    model.result("pg4").feature().duplicate("vol5", "vol4");
    model.result("pg4").feature("vol5").set("arraydim", "2");
    model.result("pg4").run();
    model.result("pg4").feature("vol5").set("looplevel", new int[]{5});
    model.result("pg4").feature().duplicate("vol6", "vol5");
    model.result("pg4").feature("vol6").set("arraydim", "2");
    model.result("pg4").run();
    model.result("pg4").feature("vol6").set("looplevel", new int[]{6});
    model.result("pg4").run();

    model.component("comp1").view("view1").set("showgrid", false);

    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").run();
    model.result("pg5").label("\u7ea6\u675f\u6a21\u6001");
    model.result("pg5").set("data", "dset5");
    model.result("pg5").set("titletype", "none");
    model.result("pg5").set("showlegends", false);
    model.result("pg5").set("plotarrayenable", true);
    model.result("pg5").set("arrayshape", "square");
    model.result("pg5").set("order", "columnmajor");
    model.result("pg5").create("vol1", "Volume");
    model.result("pg5").feature("vol1").set("arraydim", "2");
    model.result("pg5").feature("vol1").set("data", "dset5");
    model.result("pg5").feature("vol1").setIndex("looplevel", 1, 0);
    model.result("pg5").feature("vol1").set("colortable", "SpectrumLight");
    model.result("pg5").feature("vol1").create("def1", "Deform");
    model.result("pg5").run();
    model.result("pg5").feature("vol1").set("arraydim", "2");
    model.result("pg5").run();
    model.result("pg5").feature().duplicate("vol2", "vol1");
    model.result("pg5").feature("vol2").set("arraydim", "2");
    model.result("pg5").run();
    model.result("pg5").feature("vol2").setIndex("looplevel", 2, 0);
    model.result("pg5").feature().duplicate("vol3", "vol2");
    model.result("pg5").feature("vol3").set("arraydim", "2");
    model.result("pg5").run();
    model.result("pg5").feature("vol3").setIndex("looplevel", 3, 0);
    model.result("pg5").feature().duplicate("vol4", "vol3");
    model.result("pg5").feature("vol4").set("arraydim", "2");
    model.result("pg5").run();
    model.result("pg5").feature("vol4").setIndex("looplevel", 4, 0);
    model.result("pg5").feature().duplicate("vol5", "vol4");
    model.result("pg5").feature("vol5").set("arraydim", "2");
    model.result("pg5").run();
    model.result("pg5").feature("vol5").setIndex("looplevel", 5, 0);
    model.result("pg5").feature().duplicate("vol6", "vol5");
    model.result("pg5").feature("vol6").set("arraydim", "2");
    model.result("pg5").run();
    model.result("pg5").feature("vol6").setIndex("looplevel", 6, 0);
    model.result("pg5").run();
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 1, 0);
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 1, 0);
    model.result("pg3").run();
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("xlabel", "\u65f6\u95f4 (s)");
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "\u5c16\u7aef\u4f4d\u79fb (m)");
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").run();
    model.result("pg6").set("data", "dset2");
    model.result("pg6").set("titletype", "none");
    model.result("pg6").create("vol1", "Volume");
    model.result("pg6").feature("vol1").set("coloring", "uniform");
    model.result("pg6").feature("vol1").set("color", "yellow");
    model.result("pg6").feature("vol1").create("sel1", "Selection");
    model.result("pg6").feature("vol1").feature("sel1").selection().set(1);
    model.result("pg6").run();
    model.result("pg6").feature().duplicate("vol2", "vol1");
    model.result("pg6").run();
    model.result("pg6").feature("vol2").set("color", "green");
    model.result("pg6").run();
    model.result("pg6").feature("vol2").feature("sel1").selection().set(2);
    model.result("pg6").run();
    model.result("pg6").feature().duplicate("vol3", "vol2");
    model.result("pg6").run();
    model.result("pg6").feature("vol3").set("color", "blue");
    model.result("pg6").run();
    model.result("pg6").feature("vol3").feature("sel1").selection().set(3);
    model.result("pg6").run();
    model.result("pg6").create("arws1", "ArrowSurface");
    model.result("pg6").feature("arws1").set("expr", new String[]{"solid.bndl1.fax", "v", "w"});
    model.result("pg6").feature("arws1").setIndex("expr", "solid.bndl1.fay", 1);
    model.result("pg6").feature("arws1").setIndex("expr", "solid.bndl1.faz", 2);
    model.result("pg6").feature("arws1").set("placement", "elements");
    model.result("pg6").run();
    model.result("pg6").create("arws2", "ArrowSurface");
    model.result("pg6").feature("arws2").set("expr", new String[]{"solid.bndl2.fax", "v", "w"});
    model.result("pg6").feature("arws2").setIndex("expr", "solid.bndl2.fay", 1);
    model.result("pg6").feature("arws2").setIndex("expr", "solid.bndl2.faz", 2);
    model.result("pg6").feature("arws2").set("placement", "elements");
    model.result("pg6").feature("arws2").set("arrowbase", "head");
    model.result("pg6").run();
    model.result("pg6").create("tlan1", "TableAnnotation");
    model.result("pg6").feature("tlan1").set("source", "localtable");
    model.result("pg6").feature("tlan1").setIndex("localtablematrix", 0.25, 0, 0);
    model.result("pg6").feature("tlan1").setIndex("localtablematrix", 0, 0, 1);
    model.result("pg6").feature("tlan1").setIndex("localtablematrix", 0, 0, 2);
    model.result("pg6").feature("tlan1").setIndex("localtablematrix", "\u90e8\u4ef6 1", 0, 3);
    model.result("pg6").feature("tlan1").setIndex("localtablematrix", 1.25, 1, 0);
    model.result("pg6").feature("tlan1").setIndex("localtablematrix", 0, 1, 1);
    model.result("pg6").feature("tlan1").setIndex("localtablematrix", 0, 1, 2);
    model.result("pg6").feature("tlan1").setIndex("localtablematrix", "\u90e8\u4ef6 2", 1, 3);
    model.result("pg6").feature("tlan1").setIndex("localtablematrix", 2.25, 2, 0);
    model.result("pg6").feature("tlan1").setIndex("localtablematrix", 0, 2, 1);
    model.result("pg6").feature("tlan1").setIndex("localtablematrix", 0, 2, 2);
    model.result("pg6").feature("tlan1").setIndex("localtablematrix", "\u90e8\u4ef6 3", 2, 3);
    model.result("pg6").feature("tlan1").set("showpoint", false);
    model.result("pg6").run();
    model.result("pg6").run();
    model.result().remove("pg6");

    model.study("std1").feature("stat").set("useadvanceddisable", true);
    model.study("std1").feature("stat").setSolveFor("/reduced/rom1_n_rfc1_solid_1", false);
    model.study("std1").feature("stat").setSolveFor("/reduced/rom1_n_rfc1_solid_2", false);
    model.study("std1").feature("stat")
         .set("disabledreduced", new String[]{"rom1_n_rfc1_solid_1", "rom1_n_rfc1_solid_2"});
    model.study("std1").feature("stat").setSolveFor("/reduced/rom1_n_rfc1_solid_1", false);
    model.study("std1").feature("stat").setSolveFor("/reduced/rom1_n_rfc1_solid_2", false);
    model.study("std1").feature("stat").set("disabledphysics", new String[]{"solid/rfc1"});
    model.study("std1").feature("time").set("probesel", "manual");
    model.study("std1").feature("time").set("probes", new String[]{"point1"});
    model.study("std1").feature("time").set("useadvanceddisable", true);
    model.study("std1").feature("time").setSolveFor("/reduced/rom1_n_rfc1_solid_1", false);
    model.study("std1").feature("time").setSolveFor("/reduced/rom1_n_rfc1_solid_2", false);
    model.study("std1").feature("time")
         .set("disabledreduced", new String[]{"rom1_n_rfc1_solid_1", "rom1_n_rfc1_solid_2"});
    model.study("std1").feature("time").setSolveFor("/reduced/rom1_n_rfc1_solid_1", false);
    model.study("std1").feature("time").setSolveFor("/reduced/rom1_n_rfc1_solid_2", false);
    model.study("std1").feature("time").set("disabledphysics", new String[]{"solid/rfc1"});

    model.result("pg5").run();
    model.result("pg5").run();

    model.title("\u90e8\u4ef6\u6a21\u6001\u7efc\u5408\u6cd5\u6559\u7a0b");

    model
         .description("\u672c\u6559\u5b66\u6848\u4f8b\u901a\u8fc7\u4e00\u4e2a\u7b80\u5355\u7684\u6881\u5b9e\u4f53\u6a21\u578b\u4ecb\u7ecd\u201c\u90e8\u4ef6\u6a21\u6001\u7efc\u5408\u6cd5\u201d(CMS) \u7684\u6982\u5ff5\u3002\u5176\u4e2d\u5c06\u4e00\u4e9b\u6881\u90e8\u5206\u964d\u9636\u4e3a CMS \u90e8\u4ef6\uff0c\u5e76\u6267\u884c\u52a8\u6001\u548c\u9759\u6001\u5206\u6790\u3002\u6b64\u5916\uff0c\u60a8\u8fd8\u5c06\u4e86\u89e3\u5982\u4f55\u5728 CMS \u90e8\u4ef6\u4e0a\u76f4\u63a5\u65bd\u52a0\u8f7d\u8377\uff0c\u4ee5\u53ca\u5982\u4f55\u5728\u7ed3\u679c\u8ba1\u7b97\u8fc7\u7a0b\u4e2d\u4f7f\u7528\u8fd9\u4e9b\u90e8\u4ef6\u3002\u672c\u4f8b\u5c06\u6a21\u578b\u7684\u5b8c\u6574\u7248\u672c\u548c\u964d\u9636\u7248\u672c\u7684\u7ed3\u679c\u8fdb\u884c\u4e86\u6bd4\u8f83\u3002");

    model.label("cms_tutorial.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
