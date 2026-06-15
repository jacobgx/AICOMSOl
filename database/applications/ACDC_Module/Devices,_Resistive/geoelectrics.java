/*
 * geoelectrics.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:21 by COMSOL 6.3.0.290. */
public class geoelectrics {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Devices,_Resistive");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("ec", "ConductiveMedia", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/ec", true);

    model.param().set("L", "50[m]");
    model.param().descr("L", "\u57df\u957f\u5ea6");
    model.param().set("W", "50[m]");
    model.param().descr("W", "\u57df\u5bbd\u5ea6");
    model.param().set("H", "20[m]");
    model.param().descr("H", "\u57df\u9ad8\u5ea6");
    model.param().set("WI", "4[m]");
    model.param().descr("WI", "\u65e0\u9650\u5c42\u539a\u5ea6");
    model.param().set("N", "25");
    model.param().descr("N", "\u7535\u6781\u6570");
    model.param().set("a", "1[m]");
    model.param().descr("a", "\u7535\u6781\u5206\u79bb");
    model.param().set("x0", "L/2-N/2*a");
    model.param().descr("x0", "\u7b2c\u4e00\u4e2a\u7535\u6781 x \u4f4d\u7f6e");
    model.param().set("y0", "W/2");
    model.param().descr("y0", "\u7b2c\u4e00\u4e2a\u7535\u6781 y \u4f4d\u7f6e");
    model.param().set("rho0", "100[m/S]");
    model.param().descr("rho0", "\u7535\u963b\u7387");
    model.param().set("dom_C1", "39");
    model.param().descr("dom_C1", "\u6b63\u6781\u57df\u6570");
    model.param().set("dom_C2", "44");
    model.param().descr("dom_C2", "\u8d1f\u6781\u57df\u6570");

    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"L", "W", "H"});
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new String[]{"0", "0", "-H"});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("layer", "WI", 0);
    model.component("comp1").geom("geom1").feature("blk1").set("layerleft", true);
    model.component("comp1").geom("geom1").feature("blk1").set("layerright", true);
    model.component("comp1").geom("geom1").feature("blk1").set("layerfront", true);
    model.component("comp1").geom("geom1").feature("blk1").set("layerback", true);
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "vectors");
    model.component("comp1").geom("geom1").feature("pol1").set("x", "range(x0,a,x0+N*a)");
    model.component("comp1").geom("geom1").feature("pol1").set("y", "y0+0*range(0,1,N)");
    model.component("comp1").geom("geom1").feature("pol1").set("z", "0*range(0,1,N)");
    model.component("comp1").geom("geom1").run("pol1");
    model.component("comp1").geom("geom1").create("blk2", "Block");
    model.component("comp1").geom("geom1").feature("blk2").set("size", new String[]{"(N+4)*a", "(N+4)*a", "1"});
    model.component("comp1").geom("geom1").feature("blk2").setIndex("size", "N*a/3", 2);
    model.component("comp1").geom("geom1").feature("blk2").set("pos", new String[]{"x0-2*a", "y0-(N+4)*a/2", "0"});
    model.component("comp1").geom("geom1").feature("blk2").setIndex("pos", "-N*a/3", 2);
    model.component("comp1").geom("geom1").run("blk2");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").view("view1").set("transparency", false);

    model.component("comp1").func().create("an1", "Analytic");
    model.component("comp1").func("an1").set("funcname", "V_ref");
    model.component("comp1").func("an1").set("expr", "1[A]*rho0/(2*pi)*(1/abs(x-x1)-1/abs(x-x2))");
    model.component("comp1").func("an1").set("args", "x, x1, x2");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1")
         .set(1, 2, 3, 4, 5, 8, 10, 11, 15, 17, 18, 22, 23, 25, 26, 28, 33, 40, 45, 46, 54, 55, 57, 62, 69, 74, 75, 76, 77, 78, 79, 80, 81);
    model.component("comp1").selection("sel1").label("\u63a5\u5730\u8fb9\u754c");
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").set(1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 14, 15, 16, 17, 18, 19);
    model.component("comp1").selection("sel2").label("\u65e0\u9650\u5143\u57df");
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").set(1, 2, 3, 4, 5, 6, 7, 8, 11, 12, 14, 15, 16, 17, 18, 19);
    model.component("comp1").selection("sel3").geom("geom1", 3, 2, new String[]{"exterior"});
    model.component("comp1").selection("sel3").set(1, 2, 3, 4, 5, 6, 7, 8, 11, 12, 14, 15, 16, 17, 18, 19);
    model.component("comp1").selection("sel3").geom("geom1", 3, 2, new String[]{"exterior", "interior"});
    model.component("comp1").selection("sel3").set(1, 2, 3, 4, 5, 6, 7, 8, 11, 12, 14, 15, 16, 17, 18, 19);
    model.component("comp1").selection("sel3").label("\u65e0\u9650\u5143\u8fb9\u754c");

    model.component("comp1").coordSystem().create("ie1", "InfiniteElement");
    model.component("comp1").coordSystem("ie1").selection().named("sel2");
    model.component("comp1").coordSystem("ie1").set("width", "1e2*dGeomChar");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"1/rho0"});
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermittivity", new String[]{"1"});
    model.component("comp1").material("mat1").label("100 Ohmm \u5747\u8d28");

    model.component("comp1").physics("ec").create("pcs1", "PointCurrentSource", 0);
    model.component("comp1").physics("ec").feature("pcs1").selection()
         .set(29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54);
    model.component("comp1").physics("ec").feature("pcs1").set("Qjp", "1*(dom==dom_C1)-1*(dom==dom_C2)");

    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").geom(0);
    model.component("comp1").selection("sel4").label("\u70b9\u6e90");
    model.component("comp1").selection("sel4")
         .set(29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54);

    model.component("comp1").physics("ec").feature("pcs1").selection().named("sel4");
    model.component("comp1").physics("ec").create("gnd1", "Ground", 2);
    model.component("comp1").physics("ec").feature("gnd1").selection().named("sel1");

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection()
         .set(67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmax", 0.05);
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").selection().set(13);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").set("hmax", 2);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "L", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "L", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "dom_C1", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "34 39", 0);
    model.study("std1").feature("stat").setIndex("pname", "L", 1);
    model.study("std1").feature("stat").setIndex("plistarr", "", 1);
    model.study("std1").feature("stat").setIndex("punit", "m", 1);
    model.study("std1").feature("stat").setIndex("pname", "L", 1);
    model.study("std1").feature("stat").setIndex("plistarr", "", 1);
    model.study("std1").feature("stat").setIndex("punit", "m", 1);
    model.study("std1").feature("stat").setIndex("pname", "dom_C2", 1);
    model.study("std1").feature("stat").setIndex("plistarr", "49 44", 1);
    model.study("std1").feature("stat").set("pcontinuationmode", "no");
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
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature().remove("vol1");
    model.result("pg1").run();
    model.result("pg1").create("mslc1", "Multislice");
    model.result("pg1").feature("mslc1").set("xnumber", "3");
    model.result("pg1").feature("mslc1").set("ynumber", "2");
    model.result("pg1").run();

    model.component("comp1").view("view1").hideEntities().create("hide1");
    model.component("comp1").view("view1").hideEntities("hide1").named("sel2");
    model.component("comp1").view("view1").hideEntities().create("hide2");
    model.component("comp1").view("view1").hideEntities("hide2").geom("geom1", 2);
    model.component("comp1").view("view1").hideEntities("hide2").named("sel3");

    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").run();
    model.result("pg3").create("slc1", "Slice");
    model.result("pg3").feature("slc1")
         .set("expr", "with(1,ec.Jx)*with(2,ec.Jx)+with(1,ec.Jy)*with(2,ec.Jy)+with(1,ec.Jz)*with(2,ec.Jz)");
    model.result("pg3").feature("slc1").set("quickplane", "zx");
    model.result("pg3").feature("slc1").set("quickymethod", "coord");
    model.result("pg3").feature("slc1").set("quicky", "25 27 29");
    model.result("pg3").feature("slc1").set("rangecoloractive", true);
    model.result("pg3").feature("slc1").set("rangecolormin", "-1E-4");
    model.result("pg3").feature("slc1").set("rangecolormax", "1E-4");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").set("titletype", "manual");
    model.result("pg3").set("title", "\u7075\u654f\u5ea6\u56fe");
    model.result("pg3").label("\u7075\u654f\u5ea6");
    model.result().dataset().duplicate("dset2", "dset1");
    model.result().dataset("dset2").selection().geom("geom1", 2);
    model.result().dataset("dset2").selection().geom("geom1", 2);
    model.result().dataset("dset2").selection().set(37, 50);
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").run();
    model.result("pg4").label("\u8868\u9762\u7535\u52bf");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("rangecoloractive", true);
    model.result("pg4").feature("surf1").set("rangecolormin", -10);
    model.result("pg4").feature("surf1").set("rangecolormax", 10);
    model.result("pg4").run();
    model.result("pg4").create("con1", "Contour");
    model.result("pg4").feature("con1").set("levelmethod", "levels");
    model.result("pg4").feature("con1").set("levels", "-10^(range(0,-0.2,-3)) 10^(range(-3,0.2,0))");
    model.result("pg4").feature("con1").set("coloring", "uniform");
    model.result("pg4").feature("con1").set("color", "black");
    model.result("pg4").feature("con1").set("colorlegend", false);
    model.result("pg4").run();
    model.result().dataset().create("cpl1", "CutPlane");
    model.result().dataset("cpl1").set("quickplane", "zx");
    model.result().dataset("cpl1").set("quicky", "y0");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").run();
    model.result("pg5").label("\u7535\u52bf\uff0c\u5207\u9762");
    model.result("pg5").set("data", "cpl1");
    model.result("pg5").setIndex("looplevel", 1, 0);
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("rangecoloractive", true);
    model.result("pg5").feature("surf1").set("rangecolormin", -10);
    model.result("pg5").feature("surf1").set("rangecolormax", 10);
    model.result("pg5").run();
    model.result("pg5").create("con1", "Contour");
    model.result("pg5").feature("con1").set("levelmethod", "levels");
    model.result("pg5").feature("con1").set("levels", "-10^(range(0.4,-0.2,-3)) 10^(range(-3,0.2,0.4))");
    model.result("pg5").feature("con1").set("coloring", "uniform");
    model.result("pg5").feature("con1").set("color", "black");
    model.result("pg5").feature("con1").set("colorlegend", false);
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").setIndex("looplevel", 2, 0);
    model.result("pg5").run();
    model.result().dataset().create("grid1", "Grid1D");
    model.result().dataset("grid1").set("source", "function");
    model.result().dataset("grid1").set("function", "an1");
    model.result().dataset("grid1").set("parmin1", 10);
    model.result().dataset("grid1").set("parmax1", 40);
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("\u7ed3\u679c\u6bd4\u8f83");
    model.result("pg6").set("titletype", "manual");
    model.result("pg6")
         .set("title", "\u89e3\u6790\u7ed3\u679c\uff08\u7ebf\uff09\u548c\u5efa\u6a21\u7ed3\u679c\uff08\u6807\u8bb0\uff09\u4e4b\u95f4\u7684\u6bd4\u8f83");
    model.result("pg6").set("xlabelactive", true);
    model.result("pg6").set("xlabel", "\u4f4d\u7f6e (m)");
    model.result("pg6").set("ylabelactive", true);
    model.result("pg6").set("ylabel", "\u7535\u538b (V)");
    model.result("pg6").create("ptgr1", "PointGraph");
    model.result("pg6").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg6").feature("ptgr1").set("linewidth", "preference");
    model.result("pg6").feature("ptgr1").set("data", "dset1");
    model.result("pg6").feature("ptgr1").setIndex("looplevelinput", "first", 0);
    model.result("pg6").feature("ptgr1").selection().named("sel4");
    model.result("pg6").feature("ptgr1").set("expr", "abs(V)");
    model.result("pg6").feature("ptgr1").set("xdata", "expr");
    model.result("pg6").feature("ptgr1").set("xdataexpr", "x");
    model.result("pg6").feature("ptgr1").set("linecolor", "blue");
    model.result("pg6").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg6").run();
    model.result("pg6").feature("ptgr2").setIndex("looplevelinput", "last", 0);
    model.result("pg6").feature("ptgr2").set("linecolor", "green");
    model.result("pg6").run();
    model.result("pg6").create("lngr1", "LineGraph");
    model.result("pg6").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg6").feature("lngr1").set("linewidth", "preference");
    model.result("pg6").feature("lngr1").set("data", "grid1");
    model.result("pg6").feature("lngr1").set("expr", "abs(V_ref(x,17.5,32.5))");
    model.result("pg6").feature("lngr1").set("xdata", "expr");
    model.result("pg6").feature("lngr1").set("xdataexpr", "x");
    model.result("pg6").feature("lngr1").set("linecolor", "blue");
    model.result("pg6").feature().duplicate("lngr2", "lngr1");
    model.result("pg6").run();
    model.result("pg6").feature("lngr2").set("expr", "abs(V_ref(x,22.5,27.5))");
    model.result("pg6").feature("lngr2").set("linecolor", "green");
    model.result("pg6").run();
    model.result("pg6").set("ylog", true);
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").label("\u76f8\u5bf9\u8bef\u5dee");
    model.result("pg7").set("titletype", "manual");
    model.result("pg7")
         .set("title", "\u5efa\u6a21\u7535\u52bf\u4e0e\u89e3\u6790\u7535\u52bf\u4e4b\u95f4\u7684\u76f8\u5bf9\u8bef\u5dee");
    model.result("pg7").set("xlabelactive", true);
    model.result("pg7").set("xlabel", "\u4f4d\u7f6e (m)");
    model.result("pg7").set("ylabelactive", true);
    model.result("pg7").set("ylabel", "\u76f8\u5bf9\u8bef\u5dee");
    model.result("pg7").set("axislimits", true);
    model.result("pg7").set("ylog", true);
    model.result("pg7").set("xmin", 12.25);
    model.result("pg7").set("xmax", 37.75);
    model.result("pg7").set("ymin", "1e-7");
    model.result("pg7").create("lngr1", "LineGraph");
    model.result("pg7").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg7").feature("lngr1").set("linewidth", "preference");
    model.result("pg7").feature("lngr1").set("data", "dset1");
    model.result("pg7").feature("lngr1").setIndex("looplevelinput", "first", 0);
    model.result("pg7").feature("lngr1").set("expr", "abs(V-V_ref(x,17.5,32.5))/abs(V)");
    model.result("pg7").feature("lngr1").selection()
         .set(67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91);
    model.result("pg7").feature("lngr1").set("xdata", "expr");
    model.result("pg7").feature("lngr1").set("xdataexpr", "x");
    model.result("pg7").feature().duplicate("lngr2", "lngr1");
    model.result("pg7").run();
    model.result("pg7").feature("lngr2").setIndex("looplevelinput", "last", 0);
    model.result("pg7").feature("lngr2").set("expr", "abs(V-V_ref(x,22.5,27.5))/abs(V)");
    model.result("pg7").run();
    model.result("pg4").run();

    model.title("\u5730\u7535\u6b63\u6f14\u95ee\u9898");

    model
         .description("\u8fd9\u662f\u5730\u7535\u5b66\u9886\u57df\u7684\u7ecf\u5178\u6b63\u6f14\u95ee\u9898\uff0c\u5305\u542b\u7535\u963b\u7387\u5c42\u6790\u6210\u50cf ERT \u548c\u5782\u76f4\u7535\u6d4b\u6df1 VES\uff0c\u7528\u4e8e\u8ba1\u7b97\u4ece\u5176\u4ed6\u7535\u6781\u5411\u5927\u5730\u6ce8\u5165\u7535\u6d41\u540e\uff0c\u4e00\u7ec4\u7ed9\u5b9a\u7535\u6781\u7684\u7535\u52bf\u3002\u672c\u4f8b\u63cf\u8ff0 100\u00a0Ohm-m \u7684\u5747\u8d28\u5730\u5c42\u4e2d 25\u00a0\u4e2a\u7535\u6781\u7684\u4e09\u7ef4 ERT \u6b63\u6f14\u95ee\u9898\uff0c\u5e76\u63d0\u4f9b\u4eff\u771f\u7ed3\u679c\u4e0e\u89e3\u6790\u89e3\u7684\u6bd4\u8f83\u6570\u636e\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("geoelectrics.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
