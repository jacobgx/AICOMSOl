/*
 * tin_melting_front.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:26 by COMSOL 6.3.0.290. */
public class tin_melting_front {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Heat_Transfer_Module\\Phase_Change");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("ht", "HeatTransferInSolidsAndFluids", "geom1");
    model.component("comp1").physics("ht").prop("PhysicalModelProperty").set("dz", "1[m]");
    model.component("comp1").physics("ht").prop("ShapeProperty").set("order_temperature", "1");
    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");
    model.component("comp1").physics("spf").prop("AdvancedSettingProperty").set("UsePseudoTime", "1");
    model.component("comp1").physics("spf").prop("PhysicalModelProperty")
         .set("Compressibility", "WeaklyCompressible");

    model.component("comp1").multiphysics().create("nitf1", "NonIsothermalFlow", 2);
    model.component("comp1").multiphysics("nitf1").set("Fluid_physics", "spf");
    model.component("comp1").multiphysics("nitf1").set("Heat_physics", "ht");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/ht", true);
    model.study("std1").feature("time").setSolveFor("/physics/spf", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/nitf1", true);

    model.param().set("k_Sn", "60[W/(m*K)]");
    model.param().descr("k_Sn", "\u5bfc\u70ed\u7cfb\u6570");
    model.param().set("Cp_Sn", "200[J/(kg*K)]");
    model.param().descr("Cp_Sn", "\u6bd4\u70ed\u5bb9");
    model.param().set("alpha_Sn", "2.67e-4[1/K]");
    model.param().descr("alpha_Sn", "\u70ed\u81a8\u80c0\u7cfb\u6570");
    model.param().set("mu_Sn", "6e-3[Pa*s]");
    model.param().descr("mu_Sn", "\u8fd0\u52a8\u9ecf\u5ea6");
    model.param().set("rho_Sn", "7500[kg/m^3]");
    model.param().descr("rho_Sn", "\u5bc6\u5ea6");
    model.param().set("DelH", "60[kJ/kg]");
    model.param().descr("DelH", "\u7194\u5316\u6f5c\u70ed");
    model.param().set("Tf", "505[K]");
    model.param().descr("Tf", "\u7194\u70b9");
    model.param().set("Th", "508[K]");
    model.param().descr("Th", "\u70ed\u58c1\u6e29\u5ea6");
    model.param().set("Tc", "503[K]");
    model.param().descr("Tc", "\u51b7\u58c1\u6e29\u5ea6");

    model.component("comp1").geom("geom1").create("sq1", "Square");
    model.component("comp1").geom("geom1").feature("sq1").set("size", 0.1);
    model.component("comp1").geom("geom1").feature("sq1").setIndex("layer", 0.06, 0);
    model.component("comp1").geom("geom1").feature("sq1").set("layerleft", true);
    model.component("comp1").geom("geom1").feature("sq1").set("layerbottom", false);
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u9521\uff08\u56fa\u6001\uff09");
    model.component("comp1").material("mat1").selection().set(2);

    model.component("comp1").physics("spf").selection().set(1);
    model.component("comp1").physics("ht").prop("PhysicalModelProperty").set("Tref", "Tf");
    model.component("comp1").physics("ht").feature("fluid1").selection().set(1);

    model.component("comp1").material("mat1").propertyGroup("def").set("thermalconductivity", new String[]{"k_Sn"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"rho_Sn"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", new String[]{"Cp_Sn"});
    model.component("comp1").material().duplicate("mat2", "mat1");
    model.component("comp1").material("mat2").label("\u9521\uff08\u6db2\u6001\uff09");
    model.component("comp1").material("mat2").selection().set(1);
    model.component("comp1").material("mat2").propertyGroup("def").set("dynamicviscosity", new String[]{"mu_Sn"});

    model.component("comp1").physics("ht").feature("init1").set("Tinit", "Th-Xg/0.1[m]*(Th-Tc)");
    model.component("comp1").physics("spf").prop("PhysicalModelProperty").set("Compressibility", "Incompressible");
    model.component("comp1").physics("spf").prop("PhysicalModelProperty").set("IncludeGravity", true);
    model.component("comp1").physics("spf").prop("PhysicalModelProperty").set("UseReducedPressure", true);
    model.component("comp1").physics("ht").create("temp1", "TemperatureBoundary", 1);
    model.component("comp1").physics("ht").feature("temp1").selection().set(1);
    model.component("comp1").physics("ht").feature("temp1").set("T0", "Th");
    model.component("comp1").physics("ht").create("temp2", "TemperatureBoundary", 1);
    model.component("comp1").physics("ht").feature("temp2").selection().set(7);
    model.component("comp1").physics("ht").feature("temp2").set("T0", "Tc");
    model.component("comp1").physics("ht").create("pci1", "PhaseChangeInterface", 1);
    model.component("comp1").physics("ht").feature("pci1").selection().set(4);
    model.component("comp1").physics("ht").feature("pci1").set("Tpc", "Tf");
    model.component("comp1").physics("ht").feature("pci1").set("L", "DelH");
    model.component("comp1").physics("ht").feature("pci1").set("SolidSideType", "Downside");
    model.component("comp1").physics("spf").create("prpc1", "PressurePointConstraint", 0);
    model.component("comp1").physics("spf").feature("prpc1").selection().set(1);

    model.component("comp1").multiphysics("nitf1").set("BoussinesqApproximation", true);
    model.component("comp1").multiphysics("nitf1").set("SpecifyDensity", "CustomLinearizedDensity");
    model.component("comp1").multiphysics("nitf1").set("rhoref_mat", "from_mat");
    model.component("comp1").multiphysics("nitf1").set("alphap", "alpha_Sn");

    model.component("comp1").common().create("free1", "DeformingDomainDeformedGeometry");
    model.component("comp1").common("free1").selection().all();
    model.component("comp1").common("free1").set("smoothingType", "laplace");
    model.component("comp1").common().create("pnmd1", "PrescribedNormalMeshDisplacementDeformedGeometry");
    model.component("comp1").common("pnmd1").selection().set(1, 2, 3, 5, 6, 7);

    model.component("comp1").mesh("mesh1").autoMeshSize(4);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tlist", "range(0,100,10000)");
    model.study("std1").feature("time").set("usertol", true);
    model.study("std1").feature("time").set("rtol", "1e-4");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("v1").feature("comp1_material_disp").set("scalemethod", "parent");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u6e29\u5ea6 (ht)");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("solutionparams", "parent");
    model.result("pg1").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u901f\u5ea6 (spf)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").label("\u8868\u9762");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("expr", "spf.U");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u538b\u529b (spf)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").feature().create("con1", "Contour");
    model.result("pg3").feature("con1").label("\u7b49\u503c\u7ebf");
    model.result("pg3").feature("con1").set("showsolutionparams", "on");
    model.result("pg3").feature("con1").set("expr", "p");
    model.result("pg3").feature("con1").set("number", 40);
    model.result("pg3").feature("con1").set("levelrounding", false);
    model.result("pg3").feature("con1").set("smooth", "internal");
    model.result("pg3").feature("con1").set("showsolutionparams", "on");
    model.result("pg3").feature("con1").set("data", "parent");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").label("\u6e29\u5ea6\u548c\u6d41\u4f53\u6d41\u52a8 (nitf1)");
    model.result("pg4").set("showlegendsunit", true);
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").label("\u6d41\u4f53\u6e29\u5ea6");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("solutionparams", "parent");
    model.result("pg4").feature("surf1").set("expr", "nitf1.T");
    model.result("pg4").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg4").feature("surf1").set("smooth", "internal");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result("pg4").feature("surf1").feature().create("sel1", "Selection");
    model.result("pg4").feature("surf1").feature("sel1").selection().geom("geom1", 2);
    model.result("pg4").feature("surf1").feature("sel1").selection().set(1);
    model.result("pg4").feature().create("surf2", "Surface");
    model.result("pg4").feature("surf2").label("\u56fa\u4f53\u6e29\u5ea6");
    model.result("pg4").feature("surf2").set("showsolutionparams", "on");
    model.result("pg4").feature("surf2").set("solutionparams", "parent");
    model.result("pg4").feature("surf2").set("expr", "nitf1.T");
    model.result("pg4").feature("surf2").set("smooth", "internal");
    model.result("pg4").feature("surf2").set("showsolutionparams", "on");
    model.result("pg4").feature("surf2").set("data", "parent");
    model.result("pg4").feature("surf2").feature().create("sel1", "Selection");
    model.result("pg4").feature("surf2").feature("sel1").selection().geom("geom1", 2);
    model.result("pg4").feature("surf2").feature("sel1").selection().set(2);
    model.result("pg4").feature("surf2").set("inheritplot", "surf1");
    model.result("pg4").feature().create("arws1", "ArrowSurface");
    model.result("pg4").feature("arws1").label("\u6d41\u4f53\u6d41\u52a8");
    model.result("pg4").feature("arws1").set("showsolutionparams", "on");
    model.result("pg4").feature("arws1").set("solutionparams", "parent");
    model.result("pg4").feature("arws1").set("expr", new String[]{"nitf1.ux", "nitf1.uy"});
    model.result("pg4").feature("arws1").set("xnumber", 30);
    model.result("pg4").feature("arws1").set("ynumber", 30);
    model.result("pg4").feature("arws1").set("arrowtype", "cone");
    model.result("pg4").feature("arws1").set("arrowlength", "logarithmic");
    model.result("pg4").feature("arws1").set("showsolutionparams", "on");
    model.result("pg4").feature("arws1").set("data", "parent");
    model.result("pg4").feature("arws1").feature().create("col1", "Color");
    model.result("pg4").feature("arws1").feature("col1").set("showcolordata", "off");
    model.result("pg4").feature("arws1").feature("col1").set("expr", "spf.U");
    model.result("pg4").feature("arws1").feature().create("filt1", "Filter");
    model.result("pg4").feature("arws1").feature("filt1").set("expr", "spf.U>nitf1.Uave");
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").set("data", "dset1");
    model.result("pg5").setIndex("looplevel", 101, 0);
    model.result("pg5").label("\u53d8\u5f62\u51e0\u4f55");
    model.result("pg5").create("mesh1", "Mesh");
    model.result("pg5").feature("mesh1").set("meshdomain", "surface");
    model.result("pg5").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg5").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg5").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg5").feature("mesh1").create("sel1", "MeshSelection");
    model.result("pg5").feature("mesh1").feature("sel1").selection().set(1, 2);
    model.result("pg5").feature("mesh1").set("qualmeasure", "custom");
    model.result("pg5").feature("mesh1").set("qualexpr", "comp1.material.relVol");
    model.result("pg5").feature("mesh1").set("colorrangeunitinterval", false);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 101, 0);
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").feature("arws1").set("xnumber", 15);
    model.result("pg4").feature("arws1").set("ynumber", 15);
    model.result("pg4").run();
    model.result("pg4").feature("arws1").feature("filt1").set("expr", "spf.U>0.1*nitf1.Uave");
    model.result("pg4").run();
    model.result("pg2").run();
    model.result("pg2").create("arws1", "ArrowSurface");
    model.result("pg2").feature("arws1").set("expr", new String[]{"u", "v"});
    model.result("pg2").feature("arws1")
         .set("descr", "\u901f\u5ea6\u573a \uff08\u7a7a\u95f4\u548c\u6750\u6599\u5750\u6807\u7cfb\uff09");
    model.result("pg2").feature("arws1").set("arrowtype", "cone");
    model.result("pg2").feature("arws1").set("color", "black");
    model.result("pg2").run();
    model.result("pg5").run();
    model.result("pg2").run();

    model.title("\u9521\u7194\u878d\u524d\u6cbf");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u6839\u636e Stefan \u95ee\u9898\u901a\u8fc7\u79fb\u52a8\u8fb9\u754c\u9762\u8fdb\u884c\u76f8\u53d8\u5efa\u6a21\uff0c\u5b83\u7531\u4e00\u4e2a\u57fa\u51c6\u7814\u7a76\u6f14\u53d8\u800c\u6765\u3002\u6a21\u62df\u5bf9\u8c61\u4e3a\u4e00\u4e2a\u540c\u65f6\u5305\u542b\u56fa\u6001\u548c\u6db2\u6001\u9521\u7684\u65b9\u5f62\u8154\u4f53\uff0c\u5176\u5de6\u53f3\u8fb9\u754c\u5b58\u5728\u6e29\u5dee\uff0c\u4e24\u4e2a\u5206\u522b\u6c42\u89e3\u6d41\u4f53\u548c\u56fa\u4f53\u90e8\u5206\u7684\u72ec\u7acb\u57df\u4e4b\u95f4\u5171\u4eab\u79fb\u52a8\u7684\u7194\u878d\u524d\u6cbf\u8fb9\u754c\uff0c\u5e76\u6839\u636e Stefan \u80fd\u91cf\u5e73\u8861\u6761\u4ef6\u6765\u8ba1\u7b97\u6b64\u8fb9\u754c\u968f\u65f6\u95f4\u7684\u4f4d\u7f6e\u53d8\u5316\u3002\u5728\u7194\u4f53\u4e2d\uff0c\u7531\u4e8e\u5b58\u5728\u6e29\u5ea6\u68af\u5ea6\uff0c\u9884\u8ba1\u4f1a\u901a\u8fc7\u81ea\u7136\u5bf9\u6d41\u4ea7\u751f\u8fd0\u52a8\uff1b\u8be5\u8fd0\u52a8\u53cd\u8fc7\u6765\u53c8\u4f1a\u5f71\u54cd\u524d\u6cbf\u7684\u4f4d\u79fb\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("tin_melting_front.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
