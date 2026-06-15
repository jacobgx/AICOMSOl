/*
 * laser_heating_wafer.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:34 by COMSOL 6.3.0.290. */
public class laser_heating_wafer {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\COMSOL_Multiphysics\\Heat_Transfer");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("ht", "HeatTransfer", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/ht", true);

    model.param().set("r_wafer", "1[in]");
    model.param().descr("r_wafer", "\u6676\u7247\u534a\u5f84");
    model.param().set("thickness", "275[um]");
    model.param().descr("thickness", "\u6676\u7247\u539a\u5ea6");
    model.param().set("v_rotation", "10[rpm]");
    model.param().descr("v_rotation", "\u8f6c\u901f");
    model.param().set("period", "20[s]");
    model.param().descr("period", "\u6fc0\u5149\u524d\u540e\u8fd0\u52a8\u7684\u65f6\u95f4");
    model.param().set("r_spot", "2[mm]");
    model.param().descr("r_spot", "\u6fc0\u5149\u675f\u534a\u5f84");
    model.param().set("emissivity", "0.8");
    model.param().descr("emissivity", "\u6676\u7247\u7684\u8868\u9762\u8f90\u5c04\u7387");
    model.param().set("p_laser", "10[W]");
    model.param().descr("p_laser", "\u6fc0\u5149\u529f\u7387");

    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "r_wafer");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "thickness");
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"2*r_wafer", "2*r_wafer", "1"});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("size", "thickness", 2);
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new String[]{"-0.95*r_wafer", "0", "0"});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("pos", "-r_wafer", 1);
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("int1", "Intersection");
    model.component("comp1").geom("geom1").feature("int1").selection("input").set("blk1", "cyl1");
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1").set("x_focus", "r_wafer*Triangle(t/period)");
    model.component("comp1").variable("var1")
         .descr("x_focus", "\u6fc0\u5149\u7126\u70b9\u4f4d\u7f6e x \u5750\u6807");
    model.component("comp1").variable("var1").set("y_focus", "0[m]");
    model.component("comp1").variable("var1")
         .descr("y_focus", "\u6fc0\u5149\u7126\u70b9\u4f4d\u7f6e y \u5750\u6807");
    model.component("comp1").variable("var1").set("r_focus", "sqrt((x-x_focus)^2+(y-y_focus)^2)");
    model.component("comp1").variable("var1").descr("r_focus", "\u4e0e\u7126\u70b9\u7684\u95f4\u8ddd");
    model.component("comp1").variable("var1")
         .set("Flux", "((2*p_laser)/(pi*r_spot^2))*exp(-(2*r_focus^2)/r_spot^2)");
    model.component("comp1").variable("var1")
         .descr("Flux", "\u6fc0\u5149\u70ed\u901a\u91cf\uff0c\u9ad8\u65af\u5206\u5e03");

    model.component("comp1").func().create("wv1", "Wave");
    model.component("comp1").func("wv1").set("funcname", "Triangle");
    model.component("comp1").func("wv1").set("type", "triangle");
    model.component("comp1").func("wv1").set("smoothactive", false);
    model.component("comp1").func("wv1").set("period", 1);
    model.component("comp1").func("wv1").set("phase", "pi/2");

    model.component("comp1").probe().create("dom1", "Domain");
    model.component("comp1").probe("dom1").set("intsurface", true);
    model.component("comp1").probe("dom1").set("intvolume", true);
    model.component("comp1").probe("dom1").label("\u6700\u5927\u503c");
    model.component("comp1").probe("dom1").set("probename", "T_max");
    model.component("comp1").probe("dom1").set("type", "maximum");
    model.component("comp1").probe().create("dom2", "Domain");
    model.component("comp1").probe("dom2").set("intsurface", true);
    model.component("comp1").probe("dom2").set("intvolume", true);
    model.component("comp1").probe("dom2").label("\u5e73\u5747\u503c");
    model.component("comp1").probe("dom2").set("probename", "T_average");
    model.component("comp1").probe().create("dom3", "Domain");
    model.component("comp1").probe("dom3").set("intsurface", true);
    model.component("comp1").probe("dom3").set("intvolume", true);
    model.component("comp1").probe("dom3").label("\u6700\u5c0f\u503c");
    model.component("comp1").probe("dom3").set("probename", "T_min");
    model.component("comp1").probe("dom3").set("type", "minimum");
    model.component("comp1").probe().create("var1", "GlobalVariable");
    model.component("comp1").probe("var1").set("probename", "T_diff");
    model.component("comp1").probe("var1").set("expr", "T_max-T_min");

    model.component("comp1").common().create("rot1", "RotatingDomain");
    model.component("comp1").common("rot1").selection().all();
    model.component("comp1").common("rot1").set("rotationType", "rotationalVelocity");
    model.component("comp1").common("rot1").set("rotationalVelocityExpression", "constantRevolutionsPerTime");
    model.component("comp1").common("rot1").set("revolutionsPerTime", "v_rotation");

    model.component("comp1").physics("ht").create("hf1", "HeatFluxBoundary", 2);
    model.component("comp1").physics("ht").feature("hf1").selection().set(4);
    model.component("comp1").physics("ht").feature("hf1").set("q0_input", "emissivity*Flux");
    model.component("comp1").physics("ht").create("sar1", "SurfaceToAmbientRadiation", 2);
    model.component("comp1").physics("ht").feature("sar1").selection().set(4);
    model.component("comp1").physics("ht").feature("sar1").set("epsilon_rad_mat", "userdef");
    model.component("comp1").physics("ht").feature("sar1").set("epsilon_rad", "emissivity");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp1").material("mat1").label("Silicon");
    model.component("comp1").material("mat1").set("family", "custom");
    model.component("comp1").material("mat1").set("customspecular", new double[]{0.7843137254901961, 1, 1});
    model.component("comp1").material("mat1").set("diffuse", "custom");
    model.component("comp1").material("mat1")
         .set("customdiffuse", new double[]{0.6666666666666666, 0.6666666666666666, 0.7058823529411765});
    model.component("comp1").material("mat1").set("ambient", "custom");
    model.component("comp1").material("mat1")
         .set("customambient", new double[]{0.6666666666666666, 0.6666666666666666, 0.7058823529411765});
    model.component("comp1").material("mat1").set("noise", true);
    model.component("comp1").material("mat1").set("fresnel", 0.7);
    model.component("comp1").material("mat1").set("roughness", 0.5);
    model.component("comp1").material("mat1").set("diffusewrap", 0);
    model.component("comp1").material("mat1").set("reflectance", 0);
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"1e-12[S/m]", "0", "0", "0", "1e-12[S/m]", "0", "0", "0", "1e-12[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"2.6e-6[1/K]", "0", "0", "0", "2.6e-6[1/K]", "0", "0", "0", "2.6e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "700[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"11.7", "0", "0", "0", "11.7", "0", "0", "0", "11.7"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "2329[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"130[W/(m*K)]", "0", "0", "0", "130[W/(m*K)]", "0", "0", "0", "130[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "170[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "0.28");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").label("Refractive index");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"3.48", "0", "0", "0", "3.48", "0", "0", "0", "3.48"});

    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").set("facemethod", "tri");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", 1);
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 4);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tlist", "range(0,1,60)");
    model.study("std1").feature("time").set("usertol", true);
    model.study("std1").feature("time").set("rtol", "1e-3");
    model.study("std1").createAutoSequences("all");

    model.component("comp1").probe("dom1").genResult("none");
    model.component("comp1").probe("dom2").genResult("none");
    model.component("comp1").probe("dom3").genResult("none");
    model.component("comp1").probe("var1").genResult("none");

    model.sol("sol1").runAll();

    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u6e29\u5ea6 (ht)");
    model.result("pg2").feature().create("vol1", "Volume");
    model.result("pg2").feature("vol1").set("showsolutionparams", "on");
    model.result("pg2").feature("vol1").set("solutionparams", "parent");
    model.result("pg2").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg2").feature("vol1").set("smooth", "internal");
    model.result("pg2").feature("vol1").set("showsolutionparams", "on");
    model.result("pg2").feature("vol1").set("data", "parent");
    model.result("pg2").run();
    model.result("pg1").set("window", "window2");
    model.result("pg1").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.result("pg1").run();
    model.result("pg1").label("\u57df\u5185\u7684\u63a2\u9488\u6e29\u5ea6");
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").set("title", "\u57df\u5185\u6e29\u5ea6\u968f\u65f6\u95f4\u7684\u53d8\u5316");
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "\u6e29\u5ea6 (K)");
    model.result("pg1").set("legendpos", "upperleft");
    model.result("pg1").set("window", "window2");
    model.result("pg1").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.result("pg1").run();
    model.result("pg1").feature("tblp1").set("plotcolumns", new int[]{2, 3, 4});
    model.result("pg1").set("window", "window2");
    model.result("pg1").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u7b49\u6e29\u7ebf (ht)");
    model.result("pg3").feature().create("iso1", "Isosurface");
    model.result("pg3").feature("iso1").set("solutionparams", "parent");
    model.result("pg3").feature("iso1").set("number", 10);
    model.result("pg3").feature("iso1").set("colortable", "HeatCameraLight");
    model.result("pg3").feature("iso1").set("smooth", "internal");
    model.result("pg3").feature("iso1").set("showsolutionparams", "on");
    model.result("pg3").feature("iso1").set("data", "parent");
    model.result("pg3").label("\u7b49\u6e29\u7ebf (ht)");
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u57df\u5185\u6700\u5927\u6e29\u5dee");
    model.result("pg4").set("titletype", "manual");
    model.result("pg4").set("title", "\u57df\u5185\u6700\u5927\u6e29\u5dee\u968f\u65f6\u95f4\u7684\u53d8\u5316");
    model.result("pg4").create("tblp1", "Table");
    model.result("pg4").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg4").feature("tblp1").set("linewidth", "preference");
    model.result("pg4").feature("tblp1").label("\u63a2\u9488\u8868\u56fe 1");
    model.result("pg4").feature("tblp1").set("plotcolumninput", "manual");
    model.result("pg4").feature("tblp1").set("plotcolumns", new int[]{5});
    model.result("pg4").run();
    model.result("pg2").run();

    model.title("\u7845\u6676\u7247\u6fc0\u5149\u52a0\u70ed");

    model
         .description("\u672c\u4f8b\u901a\u8fc7\u6cbf\u5706\u8f68\u8ff9\u53d1\u5c04\u6fc0\u5149\u5bf9\u5de5\u4f5c\u53f0\u4e0a\u81ea\u8f6c\u7684\u7845\u6676\u7247\u52a0\u70ed\u3002\u5c06\u6fc0\u5149\u5165\u5c04\u70ed\u901a\u91cf\u4f5c\u4e3a\u6676\u7247\u8868\u9762\u5206\u5e03\u7684\u70ed\u6e90\u6765\u5904\u7406\uff0c\u5f97\u5230\u6676\u7247\u7684\u77ac\u6001\u70ed\u54cd\u5e94\u3002\u5176\u4e2d\u8ba1\u7b97\u52a0\u70ed\u8fc7\u7a0b\u4e2d\u6e29\u5ea6\u7684\u5cf0\u503c\u3001\u5e73\u5747\u503c\u548c\u6700\u5c0f\u503c\uff0c\u4ee5\u53ca\u6676\u7247\u4e2d\u7684\u6e29\u5ea6\u5206\u5e03\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("laser_heating_wafer.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
