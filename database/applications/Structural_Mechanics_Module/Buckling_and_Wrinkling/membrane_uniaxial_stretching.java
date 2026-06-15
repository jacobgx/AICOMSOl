/*
 * membrane_uniaxial_stretching.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:01 by COMSOL 6.3.0.290. */
public class membrane_uniaxial_stretching {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Buckling_and_Wrinkling");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("mbrn", "StructuralMembrane", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/mbrn", true);

    model.param().label("\u51e0\u4f55\u53c2\u6570");
    model.param().set("th", "1[mm]");
    model.param().descr("th", "\u77e9\u5f62\u677f\u7684\u539a\u5ea6");
    model.param().set("L", "1[m]");
    model.param().descr("L", "\u77e9\u5f62\u677f\u7684\u957f\u5ea6");
    model.param().set("W", "0.5[m]");
    model.param().descr("W", "\u77e9\u5f62\u677f\u7684\u5bbd\u5ea6");
    model.param().create("par2");
    model.param("par2").label("\u5404\u5411\u540c\u6027\u6750\u6599\u5c5e\u6027");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2").set("E_iso", "100[kPa]", "\u6768\u6c0f\u6a21\u91cf");
    model.param("par2").set("nu_iso", "0.3", "\u6cca\u677e\u6bd4");
    model.param().create("par3");
    model.param("par3").label("\u6b63\u4ea4\u5404\u5411\u5f02\u6027\u6750\u6599\u5c5e\u6027");

//    To import content from file, use:
//    model.param("par3").loadFile("FILENAME");
    model.param("par3").set("E1_orth", "100[kPa]", "\u6768\u6c0f\u6a21\u91cf\uff0c1 \u5206\u91cf");
    model.param("par3").set("E2_orth", "20[kPa]", "\u6768\u6c0f\u6a21\u91cf\uff0c2 \u5206\u91cf");
    model.param("par3").set("E3_orth", "20[kPa]", "\u6768\u6c0f\u6a21\u91cf\uff0c3 \u5206\u91cf");
    model.param("par3").set("nu12_orth", "0.3", "\u6cca\u677e\u6bd4\uff0c12 \u5206\u91cf");
    model.param("par3").set("nu23_orth", "0", "\u6cca\u677e\u6bd4\uff0c23 \u5206\u91cf");
    model.param("par3").set("nu13_orth", "0", "\u6cca\u677e\u6bd4\uff0c13 \u5206\u91cf");
    model.param("par3").set("G12_orth", "38.5[kPa]", "\u526a\u5207\u6a21\u91cf\uff0c12 \u5206\u91cf");
    model.param("par3").set("G23_orth", "G12_orth", "\u526a\u5207\u6a21\u91cf\uff0c23 \u5206\u91cf");
    model.param("par3").set("G13_orth", "G12_orth", "\u526a\u5207\u6a21\u91cf\uff0c13 \u5206\u91cf");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1")
         .set("Beta_ana", "max(-(mbrn.D12*mbrn.eel11+mbrn.D22*mbrn.eel22+mbrn.D23*mbrn.eel33)/mbrn.D22,0)");
    model.component("comp1").variable("var1").descr("Beta_ana", "\u8936\u76b1\u91cf\u5ea6\uff0c\u89e3\u6790\u503c");
    model.component("comp1").variable("var1").set("iswrinkled_ana", "Beta_ana>0");
    model.component("comp1").variable("var1")
         .descr("iswrinkled_ana", "\u53d1\u751f\u8936\u76b1\uff0c\u89e3\u6790\u503c");

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("size", new String[]{"L", "W"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("mbrn").feature("lemm1").set("SolidModel", "Orthotropic");
    model.component("comp1").physics("mbrn").feature("lemm1").create("wr1", "Wrinkling", 2);
    model.component("comp1").physics("mbrn").feature("to1").set("d", "th");
    model.component("comp1").physics("mbrn").create("fix1", "Fixed", 1);
    model.component("comp1").physics("mbrn").feature("fix1").selection().set(1);
    model.component("comp1").physics("mbrn").create("disp1", "Displacement1", 1);
    model.component("comp1").physics("mbrn").feature("disp1").selection().set(4);
    model.component("comp1").physics("mbrn").feature("disp1").setIndex("Direction", "prescribed", 0);
    model.component("comp1").physics("mbrn").feature("disp1").setIndex("U0", "1[mm]", 0);
    model.component("comp1").physics("mbrn").feature("disp1").setIndex("Direction", "prescribed", 1);
    model.component("comp1").physics("mbrn").feature("disp1").setIndex("Direction", "prescribed", 2);
    model.component("comp1").physics("mbrn").create("stb1", "MembraneStabilization", 2);

    model.component("comp1").material().create("sw1", "Switch");
    model.component("comp1").material("sw1").feature().create("mat1", "Common", "comp1");
    model.component("comp1").material("sw1").feature("mat1").label("\u5404\u5411\u540c\u6027\u6750\u6599");
    model.component("comp1").material("sw1").feature("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("sw1").feature("mat1").propertyGroup("Enu").set("E", new String[]{"E_iso"});
    model.component("comp1").material("sw1").feature("mat1").propertyGroup("Enu").set("nu", new String[]{"nu_iso"});
    model.component("comp1").material("sw1").feature("mat1").propertyGroup("def").set("density", new String[]{"0"});
    model.component("comp1").material("sw1").feature().create("mat2", "Common", "comp1");
    model.component("comp1").material("sw1").feature("mat2")
         .label("\u6b63\u4ea4\u5404\u5411\u5f02\u6027\u6750\u6599");
    model.component("comp1").material("sw1").feature("mat2").propertyGroup()
         .create("Orthotropic", "Orthotropic", "Orthotropic");
    model.component("comp1").material("sw1").feature("mat2").propertyGroup("Orthotropic")
         .set("Evector", new String[]{"E1_orth", "E2_orth", "E3_orth"});
    model.component("comp1").material("sw1").feature("mat2").propertyGroup("Orthotropic")
         .set("nuvector", new String[]{"nu12_orth", "nu23_orth", "nu13_orth"});
    model.component("comp1").material("sw1").feature("mat2").propertyGroup("Orthotropic")
         .set("Gvector", new String[]{"G12_orth", "G23_orth", "G13_orth"});
    model.component("comp1").material("sw1").feature("mat2").propertyGroup("def").set("density", new String[]{"0"});

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 3);
    model.component("comp1").mesh("mesh1").create("conv1", "Convert");
    model.component("comp1").mesh("mesh1").feature("conv1").set("splitmethod", "center");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").create("matsw", "MaterialSweep");
    model.study("std1").feature("matsw").setIndex("pname", "matsw.comp1.sw1", 0);
    model.study("std1").feature("matsw").setIndex("pcase", "all", 0);
    model.study("std1").feature("matsw").setIndex("plistarr", "range(1,1,2)", 0);
    model.study("std1").feature("matsw").setIndex("pname", "matsw.comp1.sw1", 0);
    model.study("std1").feature("matsw").setIndex("pcase", "all", 0);
    model.study("std1").feature("matsw").setIndex("plistarr", "range(1,1,2)", 0);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("s1").feature("fc1").set("termonres", "both");
    model.sol("sol1").feature("s1").feature("fc1").set("maxiter", 50);

    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("pm1").feature("so1").set("psol", "sol2");
    model.batch("pm1").run("compute");

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").run();
    model.result("pg1").label("\u8936\u76b1\u533a");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").set("plotarrayenable", true);
    model.result("pg1").set("arrayaxis", "y");
    model.result("pg1").set("relpadding", 0.5);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("arraydim", "1");
    model.result("pg1").feature("surf1").set("expr", "mbrn.iswrinkled");
    model.result("pg1").feature("surf1").set("descr", "\u5e26\u8936\u76b1");
    model.result("pg1").feature("surf1").set("smooth", "none");
    model.result("pg1").feature().duplicate("surf2", "surf1");
    model.result("pg1").feature("surf2").set("arraydim", "1");
    model.result("pg1").run();
    model.result("pg1").feature("surf2").set("expr", "iswrinkled_ana");
    model.result("pg1").feature("surf2").set("inheritplot", "surf1");
    model.result("pg1").feature("surf2").set("titletype", "none");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").create("ann1", "Annotation");
    model.result("pg1").feature("ann1").set("arraydim", "1");
    model.result("pg1").feature("ann1").set("text", "\u8ba1\u7b97\u503c");
    model.result("pg1").feature("ann1").set("posxexpr", "L/2");
    model.result("pg1").feature("ann1").set("showpoint", false);
    model.result("pg1").feature("ann1").set("manualindexing", true);
    model.result("pg1").run();
    model.result("pg1").create("ann2", "Annotation");
    model.result("pg1").feature("ann2").set("arraydim", "1");
    model.result("pg1").feature("ann2").set("text", "\u89e3\u6790\u503c");
    model.result("pg1").feature("ann2").set("posxexpr", "L/2");
    model.result("pg1").feature("ann2").set("posyexpr", "W");
    model.result("pg1").feature("ann2").set("showpoint", false);
    model.result("pg1").feature("ann2").set("anchorpoint", "lowerright");
    model.result("pg1").feature("ann2").set("manualindexing", true);
    model.result("pg1").feature("ann2").set("arrayindex", 1);
    model.result("pg1").run();
    model.result("pg1").set("view", "new");
    model.result("pg1").run();

    model.view("view3").set("showgrid", false);

    model.result().duplicate("pg2", "pg1");
    model.result("pg2").run();
    model.result("pg2").label("\u8936\u76b1\u91cf\u5ea6");
    model.result("pg2").feature("surf1").set("arraydim", "1");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("expr", "mbrn.lemm1.wr1.Beta");
    model.result("pg2").feature("surf1")
         .set("descr", "\u8936\u76b1\u6d4b\u91cf\uff0c\u6750\u6599\u5750\u6807\u7cfb");
    model.result("pg2").feature("surf1").set("smooth", "material");
    model.result("pg2").feature("surf2").set("arraydim", "1");
    model.result("pg2").run();
    model.result("pg2").feature("surf2").set("expr", "Beta_ana");
    model.result("pg2").feature("surf2").set("smooth", "material");
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").run();
    model.result("pg3").label("\u7b2c\u4e00\u4e3b\u5e94\u529b");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").setIndex("looplevel", 1, 0);
    model.result("pg3").set("showlegendsmaxmin", true);
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", "mbrn.sp1Gp");
    model.result("pg3").feature("surf1").set("descr", "\u7b2c\u4e00\u4e3b\u5e94\u529b");
    model.result("pg3").feature("surf1").set("colortable", "Prism");
    model.result("pg3").run();
    model.result("pg3").create("arws1", "ArrowSurface");
    model.result("pg3").feature("arws1")
         .set("expr", new String[]{"mbrn.lemm1.wr1.tnx", "mbrn.lemm1.wr1.tny", "mbrn.lemm1.wr1.tnz"});
    model.result("pg3").feature("arws1")
         .set("descr", "\u5f20\u529b\u573a\u65b9\u5411\uff0c\u7a7a\u95f4\u5750\u6807\u7cfb");
    model.result("pg3").run();
    model.result().duplicate("pg4", "pg3");
    model.result("pg4").run();
    model.result("pg4").label("\u7b2c\u4e8c\u4e3b\u5e94\u529b");
    model.result("pg4").run();
    model.result("pg4").feature("surf1").set("expr", "mbrn.sp2Gp");
    model.result("pg4").feature("surf1").set("descr", "\u7b2c\u4e8c\u4e3b\u5e94\u529b");
    model.result("pg4").run();
    model.result("pg4").feature("arws1")
         .set("expr", new String[]{"mbrn.lemm1.wr1.wnx", "mbrn.lemm1.wr1.wny", "mbrn.lemm1.wr1.wnz"});
    model.result("pg4").feature("arws1")
         .set("descr", "\u8936\u76b1\u65b9\u5411\uff0c\u7a7a\u95f4\u5750\u6807\u7cfb");
    model.result("pg4").run();
    model.result("pg1").run();

    model.nodeGroup().create("grp1", "Results");
    model.nodeGroup("grp1").set("type", "plotgroup");
    model.nodeGroup("grp1").add("plotgroup", "pg1");
    model.nodeGroup("grp1").add("plotgroup", "pg2");
    model.nodeGroup("grp1").add("plotgroup", "pg3");
    model.nodeGroup("grp1").add("plotgroup", "pg4");
    model.nodeGroup("grp1").label("\u5404\u5411\u540c\u6027\u6750\u6599");
    model.nodeGroup().duplicate("grp2", "grp1");
    model.nodeGroup("grp2").label("\u6b63\u4ea4\u5404\u5411\u5f02\u6027\u6750\u6599");

    model.result("pg5").run();
    model.result("pg5").setIndex("looplevel", 2, 0);
    model.result("pg5").run();
    model.result("pg6").run();
    model.result("pg6").setIndex("looplevel", 2, 0);
    model.result("pg6").feature("surf1").set("arraydim", "1");
    model.result("pg6").run();
    model.result("pg6").feature("surf1").set("rangecoloractive", true);
    model.result("pg6").feature("surf1").set("rangecolormax", 1.2E-5);
    model.result("pg6").run();
    model.result("pg7").run();
    model.result("pg7").setIndex("looplevel", 2, 0);
    model.result("pg7").run();
    model.result("pg8").run();
    model.result("pg8").setIndex("looplevel", 2, 0);
    model.result("pg8").run();
    model.result("pg1").run();
    model.result().duplicate("pg9", "pg1");

    model.nodeGroup("grp1").add("plotgroup", "pg9");

    model.result("pg9").run();
    model.result("pg9").set("edges", false);
    model.result("pg9").set("plotarrayenable", false);
    model.result("pg9").run();
    model.result("pg9").feature().remove("surf2");
    model.result("pg9").feature().remove("ann1");
    model.result("pg9").feature().remove("ann2");
    model.result("pg9").run();
    model.result("pg9").feature("surf1").create("def1", "Deform");
    model.result("pg9").run();
    model.result("pg9").feature("surf1").feature("def1").set("scaleactive", true);
    model.result("pg9").feature("surf1").feature("def1").set("scale", 500);
    model.result("pg9").run();
    model.result("pg9").create("arwl1", "ArrowLine");
    model.result("pg9").feature("arwl1").set("expr", new String[]{"1", "0", "0"});
    model.result("pg9").feature("arwl1").set("arrowcount", 5);
    model.result("pg9").feature("arwl1").set("scaleactive", true);
    model.result("pg9").feature("arwl1").set("scale", 0.1);
    model.result("pg9").feature("arwl1").set("inheritplot", "surf1");
    model.result("pg9").feature("arwl1").set("inheritrange", false);
    model.result("pg9").feature("arwl1").set("inheritcolor", false);
    model.result("pg9").feature("arwl1").set("inheritarrowscale", false);
    model.result("pg9").feature("arwl1").create("sel1", "Selection");
    model.result("pg9").feature("arwl1").feature("sel1").selection().set(4);
    model.result("pg9").run();
    model.result("pg9").feature("arwl1").create("def1", "Deform");
    model.result("pg9").run();
    model.result("pg9").run();
    model.result("pg9").feature().duplicate("arwl2", "arwl1");
    model.result("pg9").run();
    model.result("pg9").feature("arwl2").set("expr", new String[]{"-1", "0", "0"});
    model.result("pg9").run();
    model.result("pg9").feature("arwl2").feature("sel1").selection().set(1);
    model.result("pg9").run();
    model.result("pg9").run();
    model.result().remove("pg9");
    model.result("pg4").run();
    model.result("pg5").run();

    model.title("\u77e9\u5f62\u819c\u7684\u5355\u8f74\u62c9\u4f38");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5355\u8f74\u62c9\u4f38\u8584\u677f\u7684\u8936\u76b1\u73b0\u8c61\uff0c\u5176\u4e2d\u4f7f\u7528\u4fee\u6b63\u819c\u7406\u8bba\u7ed3\u5408\u8936\u76b1\u6a21\u578b\u6765\u6b63\u786e\u8ba1\u7b97\u8936\u76b1\u533a\u57df\u7684\u975e\u538b\u7f29\u4e3b\u5e94\u529b\u3002\u672c\u4f8b\u5c06\u89e3\u6790\u7ed3\u679c\u4e0e\u6570\u503c\u7ed3\u679c\u8fdb\u884c\u4e86\u6bd4\u8f83\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();

    model.label("membrane_uniaxial_stretching.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
