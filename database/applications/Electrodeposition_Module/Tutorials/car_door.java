/*
 * car_door.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:57 by COMSOL 6.3.0.290. */
public class car_door {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Electrodeposition_Module\\Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("cd", "PrimaryCurrentDistribution", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/cd", true);

    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "car_door.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new double[]{1, 1, 0.7});
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new double[]{-0.1, -0.03, 0});
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", 0.05);
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new double[]{-0.1, -0.03, 0.7});
    model.component("comp1").geom("geom1").feature("cyl1").set("axistype", "y");
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("arr1").selection("input").set("cyl1");
    model.component("comp1").geom("geom1").feature("arr1").set("fullsize", new int[]{3, 1, 1});
    model.component("comp1").geom("geom1").feature("arr1").set("displ", new double[]{0.5, 0, 0});
    model.component("comp1").geom("geom1").run("arr1");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("arr1", "blk1");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").init(3);
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("uni1", 1, 3, 4, 5, 6, 7);
    model.component("comp1").geom("geom1").run("del1");
    model.component("comp1").geom("geom1").create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("rot1").selection("input").set("del1", "imp1");
    model.component("comp1").geom("geom1").feature("rot1").set("rot", 90);
    model.component("comp1").geom("geom1").feature("rot1").set("axistype", "x");
    model.component("comp1").geom("geom1").feature("fin").set("repairtoltype", "relative");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").view("view1").set("transparency", true);

    model.component("comp1").geom("geom1").run("fin");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("sigma", "0.28[S/m]", "\u7535\u89e3\u8d28\u7535\u5bfc\u7387");
    model.param().set("E_cell", "250[V]", "\u5355\u5143\u6c60\u7535\u538b");
    model.param().set("Ccap", "6e-5[kg/(s*A)]", "\u5e93\u4ed1\u5bb9\u91cf");
    model.param().set("rho", "1500[kg/m^3]", "\u6f06\u819c\u5bc6\u5ea6");
    model.param().set("R_film", "5.0e8[ohm*cm]", "\u6f06\u819c\u7535\u963b\u7387");
    model.param().set("Meff", "Ccap*F_const", "\u6709\u6548\u6469\u5c14\u8d28\u91cf");
    model.param()
         .set("Eeq_O2", "1.23[V]", "\u5e73\u8861\u7535\u4f4d\uff0cO2 \u53cd\u5e94 vs. \u6807\u51c6\u6c22\u7535\u6781");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").set(2, 33, 34, 84);
    model.component("comp1").selection("sel1").label("\u9633\u6781");
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").geom(2);
    model.component("comp1").selection("sel2")
         .set(7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 29, 30, 31, 32, 36, 37, 38, 39, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 79, 80, 81, 82, 83);
    model.component("comp1").selection("sel2").label("\u9634\u6781");

    model.component("comp1").physics("cd").feature("ice1").set("sigmal_mat", "userdef");
    model.component("comp1").physics("cd").feature("ice1")
         .set("sigmal", new String[]{"sigma", "0", "0", "0", "sigma", "0", "0", "0", "sigma"});
    model.component("comp1").physics("cd").create("es1", "ElectrodeSurface", 2);
    model.component("comp1").physics("cd").feature("es1").selection().named("sel1");
    model.component("comp1").physics("cd").feature("es1").set("phisext0", "E_cell");
    model.component("comp1").physics("cd").feature("es1").feature("er1").set("Eeq", "Eeq_O2");
    model.component("comp1").physics("cd").create("tes1", "ThinElectrodeSurface", 2);
    model.component("comp1").physics("cd").feature("tes1").selection().named("sel2");
    model.component("comp1").physics("cd").feature("tes1").setIndex("Species", "s1", 0, 0);
    model.component("comp1").physics("cd").feature("tes1").setIndex("rhos", 8960, 0, 0);
    model.component("comp1").physics("cd").feature("tes1").setIndex("Ms", 0.06355, 0, 0);
    model.component("comp1").physics("cd").feature("tes1").setIndex("Species", "s1", 0, 0);
    model.component("comp1").physics("cd").feature("tes1").setIndex("rhos", 8960, 0, 0);
    model.component("comp1").physics("cd").feature("tes1").setIndex("Ms", 0.06355, 0, 0);
    model.component("comp1").physics("cd").feature("tes1").setIndex("rhos", "rho", 0, 0);
    model.component("comp1").physics("cd").feature("tes1").setIndex("Ms", "Meff", 0, 0);
    model.component("comp1").physics("cd").feature("tes1").set("FilmResistanceType", "ThicknessAndConductivity");
    model.component("comp1").physics("cd").feature("tes1").set("sf0", "0.1[um]");
    model.component("comp1").physics("cd").feature("tes1").set("sigmaf", "1/R_film");
    model.component("comp1").physics("cd").feature("tes1").set("dsf_up_src", "root.comp1.cd.sbtotu");
    model.component("comp1").physics("cd").feature("tes1").set("dsf_down_src", "root.comp1.cd.sbtotd");
    model.component("comp1").physics("cd").feature("tes1").set("BoundaryConditionType", "CurrentDensity");
    model.component("comp1").physics("cd").feature("tes1").feature("er1").setIndex("Vib", 1, 0, 0);

    model.component("comp1").mesh("mesh1").autoMeshSize(6);
    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").view("view1").set("transparency", false);

    model.study("std1").feature("time").set("tlist", "range(0,5,120)");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 25, 0);
    model.result("pg1").label("\u7535\u89e3\u8d28\u7535\u4f4d (cd)");
    model.result("pg1").create("mslc1", "Multislice");
    model.result("pg1").feature("mslc1").set("expr", new String[]{"phil"});
    model.result("pg1").create("str1", "Streamline");
    model.result("pg1").feature("str1").set("expr", new String[]{"cd.Ilx", "cd.Ily", "cd.Ilz"});
    model.result("pg1").feature("str1").set("posmethod", "start");
    model.result("pg1").feature("str1").set("pointtype", "arrow");
    model.result("pg1").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg1").feature("str1").set("color", "gray");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 25, 0);
    model.result("pg2").label("\u7535\u89e3\u8d28\u7535\u6d41\u5bc6\u5ea6 (cd)");
    model.result("pg2").create("str1", "Streamline");
    model.result("pg2").feature("str1").set("expr", new String[]{"cd.Ilx", "cd.Ily", "cd.Ilz"});
    model.result("pg2").feature("str1").set("posmethod", "start");
    model.result("pg2").feature("str1").set("pointtype", "arrow");
    model.result("pg2").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg2").feature("str1").set("color", "gray");
    model.result("pg2").feature("str1").create("col1", "Color");
    model.result("pg2").feature("str1").feature("col1").set("expr", "root.comp1.cd.IlMag");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"abs(cd.itot)"});
    model.result("pg2").feature("surf1").set("inheritplot", "str1");
    model.result("pg2").create("slit1", "SurfaceSlit");
    model.result("pg2").feature("slit1").set("upexpr", "abs(root.comp1.cd.itotu)");
    model.result("pg2").feature("slit1").set("downexpr", "abs(root.comp1.cd.itotd)");
    model.result("pg2").feature("slit1").set("inheritplot", "str1");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").setIndex("looplevel", 25, 0);
    model.result("pg3").label("\u5bf9\u5730\u7535\u6781\u7535\u4f4d (cd)");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"cd.phisext"});
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").setIndex("looplevel", 25, 0);
    model.result("pg4").label("\u7535\u6781\u7535\u4f4d vs. \u76f8\u90bb\u53c2\u6bd4\u7535\u4f4d (cd)");
    model.result("pg4").create("str1", "Streamline");
    model.result("pg4").feature("str1").set("expr", new String[]{"cd.Ilx", "cd.Ily", "cd.Ilz"});
    model.result("pg4").feature("str1").set("posmethod", "start");
    model.result("pg4").feature("str1").set("pointtype", "arrow");
    model.result("pg4").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg4").feature("str1").set("color", "gray");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"cd.Evsref"});
    model.result("pg4").create("slit1", "SurfaceSlit");
    model.result("pg4").feature("slit1").set("upexpr", "root.comp1.cd.Evsrefu");
    model.result("pg4").feature("slit1").set("downexpr", "root.comp1.cd.Evsrefd");
    model.result("pg4").feature("slit1").set("inheritplot", "surf1");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").set("data", "dset1");
    model.result("pg5").setIndex("looplevel", 25, 0);
    model.result("pg5").label("\u7535\u6781\u603b\u539a\u5ea6\u53d8\u5316 (cd)");
    model.result("pg5").create("slit1", "SurfaceSlit");
    model.result("pg5").feature("slit1").set("upexpr", "root.comp1.cd.sbtotu");
    model.result("pg5").feature("slit1").set("downexpr", "root.comp1.cd.sbtotd");
    model.result("pg5").feature("slit1").set("upunit", "\u00b5m");
    model.result("pg5").feature("slit1").set("downunit", "\u00b5m");
    model.result("pg1").run();
    model.result("pg4").run();
    model.result("pg4")
         .label("\u7535\u6781\u7535\u4f4d vs. \u76f8\u90bb\u53c2\u6bd4\u7535\u4f4d\uff0c\u6240\u6709\u7535\u6781");
    model.result().duplicate("pg6", "pg4");
    model.result("pg6").run();
    model.result("pg6")
         .label("\u7535\u6781\u7535\u4f4d vs. \u76f8\u90bb\u53c2\u6bd4\u7535\u4f4d\uff0c\u9634\u6781\u4e0a\u4fa7");
    model.result("pg6").run();
    model.result("pg6").feature("slit1").set("downexpr", "root.comp1.cd.Evsrefu");
    model.result("pg6").feature("slit1").set("inheritplot", "none");
    model.result("pg6").run();
    model.result("pg6").feature("surf1").active(false);
    model.result("pg6").run();
    model.result("pg6").run();
    model.result().duplicate("pg7", "pg6");
    model.result("pg7").run();
    model.result("pg7")
         .label("\u7535\u6781\u7535\u4f4d vs. \u76f8\u90bb\u53c2\u6bd4\u7535\u4f4d\uff0c\u9634\u6781\u4e0b\u4fa7");
    model.result("pg7").run();
    model.result("pg7").feature("slit1").set("upexpr", "root.comp1.cd.Evsrefd");
    model.result("pg7").feature("slit1").set("downexpr", "root.comp1.cd.Evsrefd");
    model.result("pg7").run();
    model.result("pg5").run();
    model.result("pg5").label("\u603b\u819c\u539a\uff0c\u9634\u6781");
    model.result().duplicate("pg8", "pg5");
    model.result("pg8").run();
    model.result("pg8").label("\u603b\u819c\u539a\uff0c\u9634\u6781\u4e0a\u4fa7");
    model.result("pg8").run();
    model.result("pg8").feature("slit1").set("downexpr", "root.comp1.cd.sbtotu");
    model.result("pg8").run();
    model.result("pg5").run();
    model.result().duplicate("pg9", "pg5");
    model.result("pg9").run();
    model.result("pg9").label("\u603b\u819c\u539a\uff0c\u9634\u6781\u4e0b\u4fa7");
    model.result("pg9").run();
    model.result("pg9").feature("slit1").set("upexpr", "root.comp1.cd.sbtotd");
    model.result("pg9").run();
    model.result().create("pg10", "PlotGroup1D");
    model.result("pg10").run();
    model.result("pg10").label("\u603b\u819c\u539a\u6bd4\u8f83");
    model.result("pg10").set("ylabelactive", true);
    model.result("pg10").set("ylabel", "\u603b\u819c\u539a (\\mu m)");
    model.result("pg10").create("ptgr1", "PointGraph");
    model.result("pg10").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg10").feature("ptgr1").set("linewidth", "preference");
    model.result("pg10").feature("ptgr1").selection().set(139);
    model.result("pg10").feature("ptgr1").set("expr", "cd.sbtotu");
    model.result("pg10").feature("ptgr1").set("descr", "\u603b\u819c\u539a\u53d8\u5316\uff0c\u5411\u4e0a");
    model.result("pg10").feature("ptgr1").set("unit", "\u00b5m");
    model.result("pg10").feature("ptgr1").set("legend", true);
    model.result("pg10").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg10").feature("ptgr1").setIndex("legends", "\u4e0a\u4fa7", 0);
    model.result("pg10").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg10").run();
    model.result("pg10").feature("ptgr2").set("expr", "cd.sbtotd");
    model.result("pg10").feature("ptgr2").set("descr", "\u603b\u819c\u539a\u53d8\u5316\uff0c\u5411\u4e0b");
    model.result("pg10").feature("ptgr2").setIndex("legends", "\u4e0b\u4fa7", 0);
    model.result("pg10").run();
    model.result("pg10").set("legendpos", "upperleft");
    model.result("pg10").run();
    model.result().export().create("anim1", "Animation");
    model.result().export("anim1").set("fontsize", "9");
    model.result().export("anim1").set("colortheme", "globaltheme");
    model.result().export("anim1").set("customcolor", new double[]{1, 1, 1});
    model.result().export("anim1").set("background", "color");
    model.result().export("anim1").set("gltfincludelines", "on");
    model.result().export("anim1").set("title1d", "on");
    model.result().export("anim1").set("legend1d", "on");
    model.result().export("anim1").set("logo1d", "on");
    model.result().export("anim1").set("options1d", "on");
    model.result().export("anim1").set("title2d", "on");
    model.result().export("anim1").set("legend2d", "on");
    model.result().export("anim1").set("logo2d", "on");
    model.result().export("anim1").set("options2d", "off");
    model.result().export("anim1").set("title3d", "on");
    model.result().export("anim1").set("legend3d", "on");
    model.result().export("anim1").set("logo3d", "on");
    model.result().export("anim1").set("options3d", "off");
    model.result().export("anim1").set("axisorientation", "on");
    model.result().export("anim1").set("grid", "on");
    model.result().export("anim1").set("axes1d", "on");
    model.result().export("anim1").set("axes2d", "on");
    model.result().export("anim1").set("showgrid", "on");
    model.result().export("anim1").set("target", "player");
    model.result().export("anim1").set("plotgroup", "pg9");
    model.result().export("anim1").run();

    model.title("\u8f66\u95e8\u7535\u6cf3\u6d82\u6f06");

    model
         .description("\u672c\u4f8b\u5728\u77ac\u6001\u4eff\u771f\u4e2d\u5bf9\u6c7d\u8f66\u95e8\u4e0a\u7684\u7535\u6cf3\u6d82\u6f06\u8fdb\u884c\u5efa\u6a21\u3002\u6f06\u5c42\u5177\u6709\u8f83\u9ad8\u7684\u7535\u963b\u7279\u6027\uff0c\u4f1a\u5bfc\u81f4\u6d82\u5c42\u7684\u6d82\u8986\u901f\u5ea6\u6025\u5267\u4e0b\u964d\u3002\n\n\u901a\u8fc7\u5c06\u4e00\u6b21\u7535\u6d41\u5206\u5e03\u63a5\u53e3\u4e0e\u819c\u963b\u76f8\u7ed3\u5408\uff0c\u63cf\u8ff0\u7535\u89e3\u6db2\u4e2d\u7684\u7535\u8377\u4f20\u8f93\u3002\n\n\u672c\u4f8b\u901a\u8fc7 CAD \u5bfc\u5165\u5f97\u5230\u4e09\u7ef4\u51e0\u4f55\u6a21\u578b\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("car_door.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
