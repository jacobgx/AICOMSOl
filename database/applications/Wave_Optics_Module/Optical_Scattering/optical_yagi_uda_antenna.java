/*
 * optical_yagi_uda_antenna.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:41 by COMSOL 6.3.0.290. */
public class optical_yagi_uda_antenna {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Wave_Optics_Module\\Optical_Scattering");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("ebem", "ElectromagneticWavesBEM", "geom1");

    model.study().create("std1");
    model.study("std1").create("wave", "Wavelength");
    model.study("std1").feature("wave").set("solnum", "auto");
    model.study("std1").feature("wave").set("notsolnum", "auto");
    model.study("std1").feature("wave").set("outputmap", new String[]{});
    model.study("std1").feature("wave").set("ngenAUX", "1");
    model.study("std1").feature("wave").set("goalngenAUX", "1");
    model.study("std1").feature("wave").set("ngenAUX", "1");
    model.study("std1").feature("wave").set("goalngenAUX", "1");
    model.study("std1").feature("wave").setSolveFor("/physics/ebem", true);

    model.component("comp1").geom("geom1").lengthUnit("nm");

    model.param().set("py", "4e-24[C*m]");
    model.param().descr("py", "\u5076\u6781\u77e9\uff0cy \u5206\u91cf");
    model.param().set("xp", "0[nm]");
    model.param().descr("xp", "\u5076\u6781\u4f4d\u7f6e\uff0cx \u5206\u91cf");
    model.param().set("yp", "-100[nm]");
    model.param().descr("yp", "\u5076\u6781\u4f4d\u7f6e\uff0cy \u5206\u91cf");
    model.param().set("zp", "0[nm]");
    model.param().descr("zp", "\u5076\u6781\u4f4d\u7f6e\uff0cz \u5206\u91cf");
    model.param().set("lda0", "570[nm]");
    model.param().descr("lda0", "\u6ce2\u957f");
    model.param().set("epsilon", "-38-10.9j");
    model.param().descr("epsilon", "\u94dd\u7684\u4ecb\u7535\u5e38\u6570");
    model.param().set("L_f", "160[nm]");
    model.param().descr("L_f", "\u9988\u7535\u5143\u4ef6\u957f\u5ea6");
    model.param().set("L_d", "0.9*L_f");
    model.param().descr("L_d", "\u5bfc\u5411\u5668\u957f\u5ea6");
    model.param().set("L_r", "1.25*L_f");
    model.param().descr("L_r", "\u53cd\u5c04\u5668\u957f\u5ea6");
    model.param().set("a_d", "lda0/4");
    model.param().descr("a_d", "\u5bfc\u5411\u5668\u95f4\u8ddd");
    model.param().set("a_r", "lda0/4.4");
    model.param().descr("a_r", "\u53cd\u5c04\u5668\u95f4\u8ddd");
    model.param().set("r", "20[nm]");
    model.param().descr("r", "\u5929\u7ebf\u534a\u5f84");

    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "r");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "L_r-2*r");
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new String[]{"-a_r", "-(L_r-2*r)/2", "0"});
    model.component("comp1").geom("geom1").feature("cyl1").set("axistype", "y");
    model.component("comp1").geom("geom1").feature().duplicate("cyl2", "cyl1");
    model.component("comp1").geom("geom1").feature("cyl2").set("h", "L_f-2*r");
    model.component("comp1").geom("geom1").feature("cyl2").setIndex("pos", 0, 0);
    model.component("comp1").geom("geom1").feature("cyl2").set("pos", new String[]{"0", "-(L_f-2*r)/2", "0"});
    model.component("comp1").geom("geom1").feature().duplicate("cyl3", "cyl2");
    model.component("comp1").geom("geom1").feature("cyl3").set("h", "L_d-2*r");
    model.component("comp1").geom("geom1").feature("cyl3").set("pos", new String[]{"0", "-(L_d-2*r)/2", "0"});
    model.component("comp1").geom("geom1").feature("cyl3").setIndex("pos", "a_d", 0);
    model.component("comp1").geom("geom1").run("cyl3");
    model.component("comp1").geom("geom1").create("sph1", "Sphere");
    model.component("comp1").geom("geom1").feature("sph1").set("r", "r");
    model.component("comp1").geom("geom1").feature("sph1").set("pos", new String[]{"0", "L_f/2-r", "0"});
    model.component("comp1").geom("geom1").feature().duplicate("sph2", "sph1");
    model.component("comp1").geom("geom1").feature("sph2").set("pos", new String[]{"0", "-L_f/2+r", "0"});
    model.component("comp1").geom("geom1").feature().duplicate("sph3", "sph2");
    model.component("comp1").geom("geom1").feature("sph3").set("pos", new String[]{"-a_r", "-L_r/2+r", "0"});
    model.component("comp1").geom("geom1").feature().duplicate("sph4", "sph3");
    model.component("comp1").geom("geom1").feature("sph4").set("pos", new String[]{"-a_r", "L_r/2-r", "0"});
    model.component("comp1").geom("geom1").feature().duplicate("sph5", "sph4");
    model.component("comp1").geom("geom1").feature("sph5").set("pos", new String[]{"a_d", "L_d/2-r", "0"});
    model.component("comp1").geom("geom1").feature().duplicate("sph6", "sph5");
    model.component("comp1").geom("geom1").feature("sph6").set("pos", new String[]{"a_d", "-L_d/2+r", "0"});
    model.component("comp1").geom("geom1").run("sph6");
    model.component("comp1").geom("geom1").create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("arr1").selection("input").set("cyl3", "sph5", "sph6");
    model.component("comp1").geom("geom1").feature("arr1").set("fullsize", new int[]{3, 1, 1});
    model.component("comp1").geom("geom1").feature("arr1").set("displ", new String[]{"a_d", "0", "0"});
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("ebem").feature("wee1").set("DisplacementFieldModel", "RelativePermittivity");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u7a7a\u6c14");
    model.component("comp1").material("mat1").selection().set();
    model.component("comp1").material("mat1").selection().allVoids();
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermittivity", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("electricconductivity", new String[]{"0"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("Al");
    model.component("comp1").material("mat2").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25);
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermittivity", new String[]{"epsilon"});
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("electricconductivity", new String[]{"0"});

    model.component("comp1").physics("ebem").prop("BackgroundField").set("SolveFor", "scatteredField");
    model.component("comp1").physics("ebem").prop("BackgroundField").set("WaveType", "ElectricPointDipole");
    model.component("comp1").physics("ebem").prop("BackgroundField")
         .set("ElectricPointDipoleType", "ElectricChargeDipole");
    model.component("comp1").physics("ebem").prop("BackgroundField").set("pQ", new String[]{"0", "py", "0"});
    model.component("comp1").physics("ebem").prop("BackgroundField").set("r0", new String[]{"xp", "yp", "zp"});
    model.component("comp1").physics("ebem").create("wee2", "WaveEquationElectric", 3);
    model.component("comp1").physics("ebem").feature("wee2").set("DisplacementFieldModel", "RelativePermittivity");
    model.component("comp1").physics("ebem").feature("wee2").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25);
    model.component("comp1").physics("ebem").create("ffc1", "FarFieldCalculation", 2);

    model.component("comp1").mesh("mesh1").autoMeshSize(6);

    model.study("std1").feature("wave").set("plist", "lda0");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u7535\u573a\uff0c\u8fb9\u754c (ebem)");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("solutionparams", "parent");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().dataset().create("grid1", "Grid3D");
    model.result().dataset("grid1").set("source", "data");
    model.result().dataset("grid1").set("data", "dset1");
    model.result().dataset("grid1").set("par1", "x");
    model.result().dataset("grid1").set("par2", "y");
    model.result().dataset("grid1").set("par3", "z");
    model.result().dataset("grid1").set("parmin1", -746.590909090909);
    model.result().dataset("grid1").set("parmax1", 1044.5454545454545);
    model.result().dataset("grid1").set("parmin2", -300);
    model.result().dataset("grid1").set("parmax2", 300);
    model.result().dataset("grid1").set("parmin3", -60.000000000000014);
    model.result().dataset("grid1").set("parmax3", 60.00000000000003);
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "grid1");
    model.result("pg2").setIndex("looplevel", 1, 0);
    model.result("pg2").create("mslc1", "Multislice");
    model.result("pg2").feature("mslc1").set("expr", new String[]{"ebem.normE"});
    model.result("pg2").feature("mslc1").set("colortable", "RainbowLight");
    model.result("pg2").label("\u7535\u573a\uff0c\u57df (ebem)");
    model.result("pg2").create("line1", "Line");
    model.result("pg2").feature("line1").set("expr", new String[]{"1"});
    model.result("pg2").feature("line1").set("data", "dset1");
    model.result("pg2").feature("line1").set("titletype", "none");
    model.result("pg2").feature("line1").set("coloring", "uniform");
    model.result("pg2").feature("line1").set("color", "black");
    model.result("pg2").feature("line1").set("solutionparams", "parent");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"ebem.normtrelE"});
    model.result("pg2").feature("surf1").set("data", "dset1");
    model.result("pg2").feature("surf1").set("inheritplot", "mslc1");
    model.result().create("pg3", "PolarGroup");
    model.result("pg3").label("\u4e8c\u7ef4\u8fdc\u573a (ebem)");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").create("rp1", "RadiationPattern");
    model.result("pg3").feature("rp1").set("legend", "on");
    model.result("pg3").feature("rp1").set("phidisc", "180");
    model.result("pg3").feature("rp1").set("expr", new String[]{"ebem.normEfar"});
    model.result("pg1").run();
    model.result().dataset("grid1").set("parmin1", -5000);
    model.result().dataset("grid1").set("parmax1", 5000);
    model.result().dataset("grid1").set("parmin2", -5000);
    model.result().dataset("grid1").set("parmax2", 5000);
    model.result().dataset("grid1").set("parmin3", -5000);
    model.result().dataset("grid1").set("parmax3", 5000);
    model.result().dataset("grid1").set("res1", 200);
    model.result().dataset("grid1").set("res2", 200);
    model.result().dataset("grid1").set("res3", 200);
    model.result("pg2").run();
    model.result("pg2").set("titletype", "none");
    model.result("pg2").run();
    model.result("pg2").feature("mslc1").set("rangecoloractive", true);
    model.result("pg2").feature("mslc1").set("rangecolormin", 0);
    model.result("pg2").feature("mslc1").set("rangecolormax", "1e8");
    model.result("pg2").feature("mslc1").set("colorscalemode", "logarithmic");
    model.result("pg2").feature("mslc1").set("colortable", "ThermalWave");

    model.view("view2").set("showaxisorientation", false);

    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").run();
    model.result("pg4").set("titletype", "none");
    model.result("pg4").create("rp1", "RadiationPattern");
    model.result("pg4").feature("rp1").set("expr", "ebem.normEfar^2");
    model.result("pg4").feature("rp1").set("thetadisc", 100);
    model.result("pg4").feature("rp1").set("phidisc", 100);
    model.result("pg4").feature("rp1").set("colorlegend", false);
    model.result("pg4").feature("rp1").create("tran1", "Transparency");
    model.result("pg4").run();
    model.result("pg4").feature("rp1").feature("tran1").set("transparency", 0.35);
    model.result("pg4").run();
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").create("mtrl1", "MaterialAppearance");
    model.result("pg4").run();

    model.view("view3").set("showaxisorientation", false);
    model.view("view3").set("showgrid", false);

    model.result("pg4").feature("surf1").feature("mtrl1").set("appearance", "custom");
    model.result("pg4").feature("surf1").feature("mtrl1").set("family", "custom");
    model.result("pg4").feature("surf1").feature("mtrl1").set("fresnel", 0.45);
    model.result("pg4").feature("surf1").feature("mtrl1").set("roughness", 0.4);
    model.result("pg4").feature("surf1").feature("mtrl1").set("anisotropy", 0.35);

    model.title("\u5149\u5b66\u516b\u6728-\u5b87\u7530\u5929\u7ebf");

    model
         .description("\u672c\u6a21\u578b\u6f14\u793a\u5982\u4f55\u5728\u201c\u7535\u78c1\u6ce2\uff0c\u8fb9\u754c\u5143\u201d\u63a5\u53e3\u4e2d\u4f7f\u7528\u8fb9\u754c\u5143\u6cd5\u6765\u6a21\u62df\u5149\u5b66\u516b\u6728-\u5b87\u7530\u5929\u7ebf\uff0c\u8fd9\u79cd\u5929\u7ebf\u7531\u7535\u70b9\u5076\u6781\u5b50\u9a71\u52a8\uff0c\u901a\u8fc7\u80cc\u666f\u573a\u5b9e\u73b0\u4fe1\u53f7\u4f20\u64ad\u3002\u672c\u4f8b\u8ba1\u7b97\u5929\u7ebf\u5468\u56f4\u7684\u573a\u5206\u5e03\u548c\u8fdc\u573a\u8f90\u5c04\u65b9\u5411\u56fe\uff0c\u663e\u793a\u51fa\u4e86\u9ad8\u65b9\u5411\u6027\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("optical_yagi_uda_antenna.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
