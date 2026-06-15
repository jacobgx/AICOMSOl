/*
 * helmholtz_coil.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:17 by COMSOL 6.3.0.290. */
public class helmholtz_coil {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Devices,_Inductive");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("mf", "InductionCurrents", "geom1");
    model.component("comp1").physics().create("mfco", "MagneticFieldsCurrentsOnly", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/mf", true);
    model.study("std1").feature("stat").setSolveFor("/physics/mfco", true);

    model.param().set("I0", "0.25[mA]");
    model.param().descr("I0", "\u7ebf\u5708\u7535\u6d41");

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("sq1", "Square");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq1").set("size", 0.05);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq1").set("base", "center");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq1").set("pos", new double[]{-0.4, 0.2});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("sq1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("sq2", "Square");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq2").set("size", 0.05);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq2").set("base", "center");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq2").set("pos", new double[]{-0.4, -0.2});
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("rev1", "Revolve");
    model.component("comp1").geom("geom1").feature("rev1").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("rev1").selection("input").set("wp1");
    model.component("comp1").geom("geom1").feature("rev1").set("angtype", "full");
    model.component("comp1").geom("geom1").run("rev1");
    model.component("comp1").geom("geom1").create("sph1", "Sphere");
    model.component("comp1").geom("geom1").feature("sph1").set("r", 1.3);
    model.component("comp1").geom("geom1").feature("sph1").setIndex("layer", 0.3, 0);
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls1").selection("vertex1").set("sph1", 4);
    model.component("comp1").geom("geom1").feature("ls1").selection("vertex2").set("sph1", 9);

    model.component("comp1").coordSystem().create("ie1", "InfiniteElement");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").coordSystem("ie1").set("ScalingType", "Spherical");
    model.component("comp1").coordSystem("ie1").selection().set(1, 2, 3, 4, 8, 9, 10, 11);

    model.component("comp1").view("view1").hideEntities().create("hide1");
    model.component("comp1").view("view1").hideEntities("hide1").geom("geom1", 2);
    model.component("comp1").view("view1").hideEntities("hide1").set(6, 10);

    model.component("comp1").physics("mf").create("coil1", "Coil", 3);
    model.component("comp1").physics("mf").feature("coil1").selection().set(6);
    model.component("comp1").physics("mf").feature("coil1").set("ConductorModel", "Multi");
    model.component("comp1").physics("mf").feature("coil1").set("CoilType", "Circular");
    model.component("comp1").physics("mf").feature("coil1").set("ICoil", "I0");
    model.component("comp1").physics("mf").feature("coil1").set("AreaFrom", "UserDefined");
    model.component("comp1").physics("mf").feature("coil1").set("HarmonicLoss", false);
    model.component("comp1").physics("mf").feature("coil1").feature("cre1").selection().set(25, 26, 46, 49);
    model.component("comp1").physics("mf").create("coil2", "Coil", 3);
    model.component("comp1").physics("mf").feature("coil2").selection().set(7);
    model.component("comp1").physics("mf").feature("coil2").set("ConductorModel", "Multi");
    model.component("comp1").physics("mf").feature("coil2").set("CoilType", "Circular");
    model.component("comp1").physics("mf").feature("coil2").set("ICoil", "I0");
    model.component("comp1").physics("mf").feature("coil2").set("AreaFrom", "UserDefined");
    model.component("comp1").physics("mf").feature("coil2").set("HarmonicLoss", false);
    model.component("comp1").physics("mf").feature("coil2").feature("cre1").selection().set(30, 31, 72, 75);
    model.component("comp1").physics("mfco").create("cond1", "Conductor", 3);
    model.component("comp1").physics("mfco").feature("cond1").selection().set(6);
    model.component("comp1").physics("mfco").feature("cond1").feature("term1").selection().set(13);
    model.component("comp1").physics("mfco").feature("cond1").feature("term1").set("I0", "10*I0");
    model.component("comp1").physics("mfco").create("cond2", "Conductor", 3);
    model.component("comp1").physics("mfco").feature("cond2").selection().set(7);
    model.component("comp1").physics("mfco").feature("cond2").feature("term1").selection().set(20);
    model.component("comp1").physics("mfco").feature("cond2").feature("term1").set("I0", "10*I0");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u7ebf\u5708\u7edd\u7f18\u4f53");
    model.component("comp1").material("mat1").selection().set(6, 7);
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermittivity", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"6e7[S/m]"});

    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").selection().set(40);
    model.component("comp1").mesh("mesh1").feature("edg1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("numelem", 50);
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().set(5, 6, 7);
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().set(6, 7);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmax", 0.05);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").create("stat2", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/mfco", false);
    model.study("std1").feature("stat2").setSolveFor("/physics/mf", false);
    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").set(6, 7);
    model.component("comp1").selection("sel1").geom("geom1", 3, 2, new String[]{"exterior"});
    model.component("comp1").selection("sel1").set(6, 7);
    model.component("comp1").selection("sel1").label("\u7ebf\u5708");

    model.result().dataset("dset1").selection().geom("geom1", 2);
    model.result().dataset("dset1").selection().named("sel1");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").run();
    model.result("pg1").label("\u78c1\u901a\u5bc6\u5ea6\uff0cMF");
    model.result("pg1").create("slc1", "Slice");
    model.result("pg1").feature("slc1").set("quickplane", "xy");
    model.result("pg1").feature("slc1").set("quickznumber", 1);
    model.result("pg1").feature("slc1").set("expr", "mf.normB");
    model.result("pg1").feature("slc1").set("descr", "\u78c1\u901a\u5bc6\u5ea6\u6a21");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").create("arwv1", "ArrowVolume");
    model.result("pg1").feature("arwv1").set("expr", new String[]{"mf.Hx", "mf.Hy", "mf.Hz"});
    model.result("pg1").feature("arwv1").set("descr", "\u78c1\u573a");
    model.result("pg1").feature("arwv1").set("xnumber", 24);
    model.result("pg1").feature("arwv1").set("ynumber", 10);
    model.result("pg1").feature("arwv1").set("znumber", 1);
    model.result("pg1").feature("arwv1").set("scaleactive", true);
    model.result("pg1").feature("arwv1").set("scale", 25);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", "1");
    model.result("pg1").feature("surf1").set("coloring", "uniform");
    model.result("pg1").feature("surf1").set("color", "white");
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("By \u7684\u6bd4\u8f83");
    model.result("pg2").create("lngr1", "LineGraph");
    model.result("pg2").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg2").feature("lngr1").set("linewidth", "preference");
    model.result("pg2").feature("lngr1").selection().set(40);
    model.result("pg2").feature("lngr1").set("expr", "mf.By");
    model.result("pg2").feature("lngr1").set("xdata", "expr");
    model.result("pg2").feature("lngr1").set("xdataexpr", "y");
    model.result("pg2").feature("lngr1").set("linemarker", "cycle");
    model.result("pg2").feature("lngr1").set("markerpos", "interp");
    model.result("pg2").feature("lngr1").set("legend", true);
    model.result("pg2").feature("lngr1").set("legendmethod", "manual");
    model.result("pg2").feature("lngr1").setIndex("legends", "\u201c\u78c1\u573a\u201d\u63a5\u53e3", 0);
    model.result("pg2").feature().duplicate("lngr2", "lngr1");
    model.result("pg2").run();
    model.result("pg2").feature("lngr2").set("expr", "mfco.By");
    model.result("pg2").feature("lngr2").set("titletype", "none");
    model.result("pg2").feature("lngr2")
         .setIndex("legends", "\u201c\u78c1\u573a\uff0c\u4ec5\u7535\u6d41\u201d\u63a5\u53e3", 0);
    model.result("pg2").run();
    model.result("pg2").set("legendpos", "uppermiddle");
    model.result("pg2").run();
    model.result().duplicate("pg3", "pg2");
    model.result("pg3").run();
    model.result("pg3").label("Byy \u7684\u6bd4\u8f83");
    model.result("pg3").set("legendpos", "lowerright");
    model.result("pg3").run();
    model.result("pg3").feature("lngr1").set("expr", "d(laginterp(2,mf.By),y)");
    model.result("pg3").run();
    model.result("pg3").feature("lngr2").set("expr", "d(mfco.By,y)");
    model.result("pg3").run();
    model.result("pg1").run();

    model.title("\u4ea5\u59c6\u970d\u5179\u7ebf\u5708\u7684\u78c1\u573a");

    model
         .description("\u4ea5\u59c6\u970d\u5179\u7ebf\u5708\u662f\u4e00\u5bf9\u5e73\u884c\u4e14\u76f8\u540c\u7684\u5706\u5f62\u7ebf\u5708\uff0c\u5b83\u4eec\u76f8\u8ddd\u4e00\u4e2a\u534a\u5f84\u7684\u957f\u5ea6\uff0c\u5e76\u7f20\u7ed5\u4ee5\u5f62\u6210\u7ed5\u7ec4\uff0c\u4f7f\u7535\u6d41\u6cbf\u540c\u4e00\u65b9\u5411\u6d41\u8fc7\u4e24\u4e2a\u7ebf\u5708\u3002\u56e0\u6b64\uff0c\u53ef\u4ee5\u5728\u8fd9\u4e24\u4e2a\u7ebf\u5708\u4e4b\u95f4\u4ea7\u751f\u5747\u5300\u7684\u78c1\u573a\uff0c\u5176\u4e3b\u5206\u91cf\u4e0e\u4e24\u4e2a\u7ebf\u5708\u7684\u8f74\u5e73\u884c\u3002\u8be5\u6a21\u578b\u6f14\u793a\u5982\u4f55\u4f7f\u7528\u4e24\u4e2a\u4e0d\u540c\u7684\u63a5\u53e3\u6765\u8ba1\u7b97\u78c1\u573a\u53ca\u5176\u9ad8\u9636\u5bfc\u6570\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("helmholtz_coil.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
