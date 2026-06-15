/*
 * multiturn_coil_asymmetric_conductor.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:24 by COMSOL 6.3.0.290. */
public class multiturn_coil_asymmetric_conductor {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Verifications");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("mf", "InductionCurrents", "geom1");

    model.study().create("std1");

    model.component("comp1").geom("geom1")
         .insertFile("multiturn_coil_asymmetric_conductor_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").feature("dif1").label("\u5bfc\u4f53");
    model.component("comp1").geom("geom1").feature("dif1").set("selresult", true);
    model.component("comp1").geom("geom1").feature("ext1").label("\u7ebf\u5708");
    model.component("comp1").geom("geom1").feature("ext1").set("selresult", true);

    model.component("comp1").view("view1").hideObjects().create("hide1");
    model.component("comp1").view("view1").hideObjects("hide1").init(2);
    model.component("comp1").view("view1").hideObjects("hide1").add("fin", 1, 2, 3, 4, 5, 52);

    model.component("comp1").physics("mf").create("als1", "AmperesLawSolid", 3);
    model.component("comp1").physics("mf").feature("als1").selection().named("geom1_dif1_dom");
    model.component("comp1").physics("mf").setGroupBySpaceDimension(true);
    model.component("comp1").physics("mf").create("coil1", "Coil", 3);
    model.component("comp1").physics("mf").feature("coil1").selection().named("geom1_ext1_dom");
    model.component("comp1").physics("mf").feature("coil1").set("ConductorModel", "Multi");
    model.component("comp1").physics("mf").feature("coil1").set("N", 2742);
    model.component("comp1").physics("mf").feature("coil1").feature("ccc1").feature("ct1").selection().set(37);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup().create("linzRes", "linzRes", "Linearized resistivity");
    model.component("comp1").material("mat1").label("Copper");
    model.component("comp1").material("mat1").set("family", "copper");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "385[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("emissivity", "0.5");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "8940[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "126e9[Pa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "0.34");
    model.component("comp1").material("mat1").propertyGroup("linzRes").label("Linearized resistivity");
    model.component("comp1").material("mat1").propertyGroup("linzRes").set("rho0", "1.667e-8[ohm*m]");
    model.component("comp1").material("mat1").propertyGroup("linzRes").set("alpha", "3.862e-3[1/K]");
    model.component("comp1").material("mat1").propertyGroup("linzRes").set("Tref", "293.15[K]");
    model.component("comp1").material("mat1").propertyGroup("linzRes").addInput("temperature");
    model.component("comp1").material("mat1").selection().named("geom1_ext1_dom");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").selection().named("geom1_dif1_dom");
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"3.526e7"});
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermittivity", new String[]{"1"});
    model.component("comp1").material("mat2").label("\u94dd");

    model.component("comp1").mesh("mesh1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("size1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("size1").selection().named("geom1_dif1_dom");
    model.component("comp1").mesh("mesh1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmax", 12);
    model.component("comp1").mesh("mesh1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("size2").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("size2").selection().named("geom1_ext1_dom");
    model.component("comp1").mesh("mesh1").feature("size2").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size2").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("size2").set("hmax", 20);
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").run("ftet1");

    model.study("std1").create("ccc", "CoilCurrentCalculation");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").set("plist", "50 200");
    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").run();
    model.result("pg1").label("\u7ebf\u5708\u65b9\u5411\u548c\u611f\u5e94\u7535\u6d41\u5bc6\u5ea6\uff0c50 Hz");
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").create("arwv1", "ArrowVolume");
    model.result("pg1").feature("arwv1").create("sel1", "Selection");
    model.result("pg1").feature("arwv1").feature("sel1").selection().named("geom1_ext1_dom");
    model.result("pg1").run();
    model.result("pg1").feature("arwv1")
         .set("expr", new String[]{"mf.coil1.eCoilx", "mf.coil1.eCoily", "mf.coil1.eCoilz"});
    model.result("pg1").feature("arwv1").set("descr", "\u7ebf\u5708\u65b9\u5411");
    model.result("pg1").feature("arwv1").set("xnumber", 10);
    model.result("pg1").feature("arwv1").set("ynumber", 10);
    model.result("pg1").feature("arwv1").set("znumber", 5);
    model.result("pg1").feature("arwv1").set("scaleactive", true);
    model.result("pg1").feature("arwv1").set("scale", 20);
    model.result("pg1").run();
    model.result("pg1").create("arwv2", "ArrowVolume");
    model.result("pg1").feature("arwv2").create("sel1", "Selection");
    model.result("pg1").feature("arwv2").feature("sel1").selection().named("geom1_dif1_dom");
    model.result("pg1").run();
    model.result("pg1").feature("arwv2").set("expr", new String[]{"mf.Jix", "mf.Jiy", "mf.Jiz"});
    model.result("pg1").feature("arwv2").set("descr", "\u4f20\u5bfc\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg1").feature("arwv2").set("xnumber", 20);
    model.result("pg1").feature("arwv2").set("ynumber", 20);
    model.result("pg1").feature("arwv2").set("arrowzmethod", "coord");
    model.result("pg1").feature("arwv2").set("zcoord", 19);
    model.result("pg1").feature("arwv2").set("color", "black");
    model.result("pg1").feature("arwv2").set("arrowtype", "cone");
    model.result("pg1").run();
    model.result("pg1").create("vol1", "Volume");
    model.result("pg1").feature("vol1").create("sel1", "Selection");
    model.result("pg1").feature("vol1").feature("sel1").selection().named("geom1_dif1_dom");
    model.result("pg1").run();
    model.result("pg1").feature("vol1").set("expr", "mf.Jiy");
    model.result("pg1").feature("vol1").set("descr", "\u4f20\u5bfc\u7535\u6d41\u5bc6\u5ea6\uff0cy \u5206\u91cf");
    model.result("pg1").run();
    model.result("pg1").set("edges", false);
    model.result("pg1").run();
    model.result().duplicate("pg2", "pg1");
    model.result("pg2").run();
    model.result("pg2").label("\u7ebf\u5708\u65b9\u5411\u548c\u611f\u5e94\u7535\u6d41\u5bc6\u5ea6\uff0c200 Hz");
    model.result("pg2").setIndex("looplevel", 2, 0);
    model.result("pg2").run();
    model.result().dataset().create("cln1", "CutLine3D");
    model.result().dataset("cln1").setIndex("genpoints", 72, 0, 1);
    model.result().dataset("cln1").setIndex("genpoints", 34, 0, 2);
    model.result().dataset("cln1").setIndex("genpoints", 288, 1, 0);
    model.result().dataset("cln1").setIndex("genpoints", 72, 1, 1);
    model.result().dataset("cln1").setIndex("genpoints", 34, 1, 2);
    model.result().dataset().duplicate("cln2", "cln1");
    model.result().dataset("cln2").setIndex("genpoints", 18.99, 0, 2);
    model.result().dataset("cln2").setIndex("genpoints", 18.99, 1, 2);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").importData("multiturn_coil_asymmetric_conductor_table1.txt");
    model.result().table("tbl1").setIndex("headers", "x [mm]", 0, 1);
    model.result().table("tbl1").setIndex("headers", "Bz(x,72,34) at 50Hz", 1, 1);
    model.result().table("tbl1").setIndex("headers", "Bz(x,72,34) at 200Hz", 2, 1);
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").importData("multiturn_coil_asymmetric_conductor_table2.txt");
    model.result().table("tbl2").setIndex("headers", "x [mm]", 0, 1);
    model.result().table("tbl2").setIndex("headers", "Jy(x,72,19) at 50Hz", 1, 1);
    model.result().table("tbl2").setIndex("headers", "Jy(x,72,19) at 200Hz", 2, 1);
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").create("lngr1", "LineGraph");
    model.result("pg3").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg3").feature("lngr1").set("linewidth", "preference");
    model.result("pg3").feature("lngr1").set("data", "cln1");
    model.result("pg3").feature("lngr1").set("solutionparams", "parent");
    model.result("pg3").feature("lngr1").set("expr", "sign(real(mf.Bz))*abs(mf.Bz)");
    model.result("pg3").feature("lngr1").set("unit", "mT");
    model.result("pg3").feature("lngr1").set("legend", true);
    model.result("pg3").feature("lngr1").set("legendmethod", "evaluated");
    model.result("pg3").feature("lngr1").set("legendpattern", "eval(freq) Hz");
    model.result("pg3").run();
    model.result("pg3").label("Bz(x,72,34)");
    model.result("pg3").create("tblp1", "Table");
    model.result("pg3").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg3").feature("tblp1").set("linewidth", "preference");
    model.result("pg3").feature("tblp1").set("xaxisdata", 1);
    model.result("pg3").feature("tblp1").set("plotcolumninput", "manual");
    model.result("pg3").feature("tblp1").set("plotcolumns", new int[]{2});
    model.result("pg3").feature("tblp1").set("linestyle", "none");
    model.result("pg3").feature("tblp1").set("linecolor", "blue");
    model.result("pg3").feature("tblp1").set("linemarker", "circle");
    model.result("pg3").feature().duplicate("tblp2", "tblp1");
    model.result("pg3").run();
    model.result("pg3").feature("tblp2").set("plotcolumns", new int[]{3});
    model.result("pg3").feature("tblp2").set("linecolor", "green");
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").create("lngr1", "LineGraph");
    model.result("pg4").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg4").feature("lngr1").set("linewidth", "preference");
    model.result("pg4").feature("lngr1").set("data", "cln2");
    model.result("pg4").feature("lngr1").set("solutionparams", "parent");
    model.result("pg4").feature("lngr1").set("expr", "sign(real(mf.Jy))*abs(mf.Jy)");
    model.result("pg4").feature("lngr1").set("legend", true);
    model.result("pg4").feature("lngr1").set("legendmethod", "evaluated");
    model.result("pg4").feature("lngr1").set("legendpattern", "eval(freq) Hz");
    model.result("pg4").run();
    model.result("pg4").label("Jy(x,72,19)");
    model.result("pg4").create("tblp1", "Table");
    model.result("pg4").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg4").feature("tblp1").set("linewidth", "preference");
    model.result("pg4").feature("tblp1").set("table", "tbl2");
    model.result("pg4").feature("tblp1").set("xaxisdata", 1);
    model.result("pg4").feature("tblp1").set("plotcolumninput", "manual");
    model.result("pg4").feature("tblp1").set("plotcolumns", new int[]{2});
    model.result("pg4").feature("tblp1").set("linestyle", "none");
    model.result("pg4").feature("tblp1").set("linecolor", "blue");
    model.result("pg4").feature("tblp1").set("linemarker", "circle");
    model.result("pg4").feature().duplicate("tblp2", "tblp1");
    model.result("pg4").run();
    model.result("pg4").feature("tblp2").set("plotcolumns", new int[]{3});
    model.result("pg4").feature("tblp2").set("linecolor", "green");
    model.result("pg4").run();
    model.result("pg2").run();
    model.result("pg2").run();

    model.title("\u4e0d\u5bf9\u79f0\u5bfc\u4f53\u677f\u4e0a\u7684\u591a\u531d\u7ebf\u5708");

    model
         .description("\u672c\u4f8b\u6c42\u89e3\u201c\u6d4b\u8bd5\u7535\u78c1\u5206\u6790\u6cd5\u201d(TEAM) \u95ee\u9898 7\u201c\u5e26\u5b54\u7684\u4e0d\u5bf9\u79f0\u5bfc\u4f53\u201d\uff0c\u8fd9\u662f\u4e00\u4e2a\u57fa\u51c6\u95ee\u9898\uff0c\u8ba1\u7b97\u5c06\u94dd\u5bfc\u4f53\u4e0d\u5bf9\u79f0\u5730\u653e\u7f6e\u5728\u901a\u4ee5\u6b63\u5f26\u65f6\u53d8\u7535\u6d41\u7684\u591a\u531d\u7ebf\u5708\u4e0a\u65f6\u4ea7\u751f\u7684\u6da1\u6d41\u548c\u78c1\u573a\u3002\u5c06\u7a7a\u95f4\u4e2d\u6307\u5b9a\u4f4d\u7f6e\u7684\u4eff\u771f\u7ed3\u679c\u4e0e\u6587\u732e\u4e2d\u7684\u6d4b\u91cf\u6570\u636e\u8fdb\u884c\u6bd4\u8f83\uff0c\u7ed3\u679c\u975e\u5e38\u4e00\u81f4\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("multiturn_coil_asymmetric_conductor.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
