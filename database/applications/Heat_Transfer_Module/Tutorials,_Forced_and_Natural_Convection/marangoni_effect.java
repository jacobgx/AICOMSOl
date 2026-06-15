/*
 * marangoni_effect.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:34 by COMSOL 6.3.0.290. */
public class marangoni_effect {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Heat_Transfer_Module\\Tutorials,_Forced_and_Natural_Convection");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");
    model.component("comp1").physics("spf").prop("AdvancedSettingProperty").set("UsePseudoTime", "1");
    model.component("comp1").physics("spf").prop("PhysicalModelProperty")
         .set("Compressibility", "WeaklyCompressible");
    model.component("comp1").physics().create("ht", "HeatTransferInFluids", "geom1");
    model.component("comp1").physics("ht").prop("PhysicalModelProperty").set("dz", "1[m]");
    model.component("comp1").physics("ht").prop("ShapeProperty").set("order_temperature", "1");

    model.component("comp1").multiphysics().create("nitf1", "NonIsothermalFlow", 2);
    model.component("comp1").multiphysics("nitf1").set("Fluid_physics", "spf");
    model.component("comp1").multiphysics("nitf1").set("Heat_physics", "ht");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/spf", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/nitf1", true);

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"10[mm]", "5[mm]"});
    model.component("comp1").geom("geom1").run("fin");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("T_right", "273.15[K]", "\u53f3\u8fb9\u754c\u7684\u6e29\u5ea6");
    model.param().set("DeltaT", "1e-3[K]", "\u5de6\u8fb9\u754c\u7684\u8d85\u6e29");
    model.param().set("gamma", "-8e-5[N/(m*K)]", "\u8868\u9762\u5f20\u529b\u6e29\u5ea6\u5bfc\u6570");
    model.param().set("rho1", "760[kg/m^3]", "\u6d41\u4f53\u5bc6\u5ea6");
    model.param().set("mu1", "4.94e-4[Pa*s]", "\u52a8\u529b\u9ecf\u5ea6");
    model.param().set("k1", "0.1[W/(m*K)]", "\u5bfc\u70ed\u7cfb\u6570");
    model.param().set("Cp1", "2090[J/(kg*K)]", "\u70ed\u5bb9");
    model.param().set("alphap1", "1.3e-3[1/K]", "\u70ed\u81a8\u80c0\u7cfb\u6570");
    model.param().set("T_ref", "T_right", "\u6750\u6599\u5c5e\u6027\u7684\u53c2\u8003\u6e29\u5ea6");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("deltaT", "T-T_right");
    model.component("comp1").variable("var1")
         .descr("deltaT", "\u6a21\u578b\u57df\u4e2d\u7684\u8d85\u5e38\u6e29\u5ea6");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u7845\u6cb9");
    model.component("comp1").material("mat1").propertyGroup("def").set("dynamicviscosity", new String[]{"mu1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("thermalconductivity", new String[]{"k1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", new String[]{"Cp1"});

    model.component("comp1").physics("spf").prop("PhysicalModelProperty").set("IncludeGravity", true);
    model.component("comp1").physics("spf").prop("PhysicalModelProperty").set("Compressibility", "Incompressible");
    model.component("comp1").physics("spf").create("wallbc2", "WallBC", 1);
    model.component("comp1").physics("spf").feature("wallbc2").selection().set(3);
    model.component("comp1").physics("spf").feature("wallbc2").set("BoundaryCondition", "Slip");
    model.component("comp1").physics("spf").create("prpc1", "PressurePointConstraint", 0);
    model.component("comp1").physics("spf").feature("prpc1").selection().set(1);
    model.component("comp1").physics("ht").prop("PhysicalModelProperty").set("Tref", "T_ref");
    model.component("comp1").physics("ht").feature("init1").set("Tinit", "T_right");
    model.component("comp1").physics("ht").create("temp1", "TemperatureBoundary", 1);
    model.component("comp1").physics("ht").feature("temp1").selection().set(4);
    model.component("comp1").physics("ht").feature("temp1").set("T0", "T_right");
    model.component("comp1").physics("ht").create("temp2", "TemperatureBoundary", 1);
    model.component("comp1").physics("ht").feature("temp2").selection().set(1);
    model.component("comp1").physics("ht").feature("temp2").set("T0", "T_right+DeltaT");

    model.component("comp1").multiphysics("nitf1").set("BoussinesqApproximation", true);
    model.component("comp1").multiphysics("nitf1").set("SpecifyDensity", "CustomLinearizedDensity");
    model.component("comp1").multiphysics("nitf1").set("rhoref", "rho1");
    model.component("comp1").multiphysics("nitf1").set("alphap", "alphap1");
    model.component("comp1").multiphysics().create("mar1", "Marangoni", 1);
    model.component("comp1").multiphysics("mar1").selection().set(3);
    model.component("comp1").multiphysics("mar1").set("sigma", "gamma*T");

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().set(3);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", "1e-4");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").selection().geom("geom1", 0);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").selection().set(2, 4);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("hmax", "2e-5");
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 2);
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hgrad", 1.1);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "T_right", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "K", 0);
    model.study("std1").feature("stat").setIndex("pname", "T_right", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "K", 0);
    model.study("std1").feature("stat").setIndex("pname", "DeltaT", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "1e-3 5e-2 2", 0);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u901f\u5ea6 (spf)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u538b\u529b (spf)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").feature().create("con1", "Contour");
    model.result("pg2").feature("con1").label("\u7b49\u503c\u7ebf");
    model.result("pg2").feature("con1").set("showsolutionparams", "on");
    model.result("pg2").feature("con1").set("expr", "p");
    model.result("pg2").feature("con1").set("number", 40);
    model.result("pg2").feature("con1").set("levelrounding", false);
    model.result("pg2").feature("con1").set("smooth", "internal");
    model.result("pg2").feature("con1").set("showsolutionparams", "on");
    model.result("pg2").feature("con1").set("data", "parent");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u6e29\u5ea6 (ht)");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("solutionparams", "parent");
    model.result("pg3").feature("surf1").set("expr", "T");
    model.result("pg3").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
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
    model.result("pg4").feature("arws1").feature().create("filt1", "Filter");
    model.result("pg4").feature("arws1").feature("filt1").set("expr", "spf.U>nitf1.Uave");
    model.result("pg1").run();
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").label("\u7b49\u6e29\u7ebf (ht)");
    model.result("pg5").feature().create("con1", "Contour");
    model.result("pg5").feature("con1").set("solutionparams", "parent");
    model.result("pg5").feature("con1").set("expr", "T");
    model.result("pg5").feature("con1").set("colortable", "HeatCameraLight");
    model.result("pg5").feature("con1").set("smooth", "internal");
    model.result("pg5").feature("con1").set("showsolutionparams", "on");
    model.result("pg5").feature("con1").set("data", "parent");
    model.result("pg5").label("\u7b49\u6e29\u7ebf (ht)");
    model.result("pg5").run();
    model.result("pg5").setIndex("looplevel", 1, 0);
    model.result("pg5").run();
    model.result("pg5").feature("con1").set("expr", "deltaT");
    model.result("pg5").feature("con1").set("descr", "\u6a21\u578b\u57df\u4e2d\u7684\u8d85\u5e38\u6e29\u5ea6");
    model.result("pg5").feature("con1").set("coloring", "uniform");
    model.result("pg5").feature("con1").set("color", "black");
    model.result("pg5").feature("con1").set("colorlegend", false);
    model.result("pg5").run();
    model.result("pg5").create("arws1", "ArrowSurface");
    model.result("pg5").feature("arws1").set("color", "black");
    model.result("pg5").run();
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", "deltaT");
    model.result("pg5").feature("surf1").set("descr", "\u6a21\u578b\u57df\u4e2d\u7684\u8d85\u5e38\u6e29\u5ea6");
    model.result("pg5").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").setIndex("looplevel", 2, 0);
    model.result("pg5").run();
    model.result("pg5").setIndex("looplevel", 3, 0);
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").run();
    model.result("pg6").label("\u5bf9\u6d41\u6c60");
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", "T");
    model.result("pg6").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg6").create("str1", "Streamline");
    model.result("pg6").feature("str1").set("descractive", true);
    model.result("pg6").feature("str1").set("descr", "\u901f\u5ea6\u573a (m/s)");
    model.result("pg6").feature("str1").set("posmethod", "magnitude");
    model.result("pg6").feature("str1").set("pointtype", "arrow");
    model.result("pg6").feature("str1").set("maxsteps", 20000);
    model.result("pg6").feature("str1").create("col1", "Color");
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").setIndex("looplevel", 1, 0);
    model.result("pg6").run();
    model.result("pg6").stepNext(0);
    model.result("pg6").run();
    model.result("pg6").stepNext(0);
    model.result("pg6").run();

    model.title("\u9a6c\u5170\u6208\u5c3c\u6548\u5e94");

    model
         .description("\u8fd9\u662f\u4e00\u4e2a\u8154\u4f53\u4e2d\u5145\u6ee1\u7845\u6cb9\u7684\u9a6c\u5170\u6208\u5c3c\u5bf9\u6d41\u6548\u5e94\u7684\u6a21\u578b\u3002\u5f53\u6e29\u5ea6\u68af\u5ea6\u5927\u65f6\uff0c\u6d41\u901f\u52a0\u5feb\u3002\u4e0e\u5bf9\u6d41\u76f8\u6bd4\uff0c\u70ed\u4f20\u5bfc\u7684\u5f71\u54cd\u76f8\u5bf9\u8f83\u5c0f\u3002");

    model.label("marangoni_effect.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
