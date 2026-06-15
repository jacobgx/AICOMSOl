/*
 * multilateral_well.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:31 by COMSOL 6.3.0.290. */
public class multilateral_well {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Subsurface_Flow_Module\\Poromechanics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");
    model.component("comp1").physics("solid").prop("StructuralTransientBehavior")
         .set("StructuralTransientBehavior", "Quasistatic");
    model.component("comp1").physics().create("dl", "PorousMediaFlowDarcy", "geom1");
    model.component("comp1").physics("dl").feature("porous1").set("storageModelType", new String[]{"poroelastic"});

    model.component("comp1").multiphysics().create("poro1", "PoroelasticCoupling", 3);
    model.component("comp1").multiphysics("poro1").set("Solid_physics", "solid");
    model.component("comp1").multiphysics("poro1").set("PorousMediaFlow_physics", "dl");
    model.component("comp1").multiphysics("poro1").selection().all();

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std1").feature("stat").setSolveFor("/physics/dl", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/poro1", true);

    model.component("comp1").geom("geom1").lengthUnit("in");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new int[]{80, 80, 40});
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new int[]{-40, -40, -40});
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", 4.25);
    model.component("comp1").geom("geom1").feature("cyl1").set("h", 80);
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new int[]{-6, -40, 0});
    model.component("comp1").geom("geom1").feature("cyl1").set("axistype", "y");
    model.component("comp1").geom("geom1").feature().duplicate("cyl2", "cyl1");
    model.component("comp1").geom("geom1").feature("cyl2").set("pos", new double[]{-6, -12.5, 0});
    model.component("comp1").geom("geom1").run("cyl2");
    model.component("comp1").geom("geom1").create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("rot1").selection("input").set("cyl2");
    model.component("comp1").geom("geom1").feature("rot1").set("rot", -20.5);
    model.component("comp1").geom("geom1").feature("rot1").set("pos", new double[]{-6, -12.5, 0});
    model.component("comp1").geom("geom1").run("rot1");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("blk1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("cyl1", "rot1");
    model.component("comp1").geom("geom1").run("fin");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("p_r", "122.45[psi]", "\u50a8\u5c42\u4e2d\u7684\u538b\u529b");
    model.param().set("p_w", "0[psi]", "\u4e95\u4e2d\u7684\u538b\u529b");
    model.param().set("So", "850[psi]", "\u5e93\u4ed1\u5185\u805a\u529b");
    model.param().set("phi", "31[deg]", "\u6469\u64e6\u89d2");
    model.param().set("C1", "14.7", "\u6821\u51c6\u5e38\u6570 1");
    model.param().set("C2", "40", "\u6821\u51c6\u5e38\u6570 2");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("N", "2*So*cos(phi)/(1-sin(phi))");
    model.component("comp1").variable("var1").descr("N", "\u5931\u6548\u53c2\u6570 1");
    model.component("comp1").variable("var1").set("Q", "(1+sin(phi))/(1-sin(phi))");
    model.component("comp1").variable("var1").descr("Q", "\u5931\u6548\u53c2\u6570 2");
    model.component("comp1").variable("var1")
         .set("fail", "(((solid.sp3+C1*(p_r-p))-Q*(solid.sp1+C1*(p_r-p))+N*(1+(solid.sp2-solid.sp1)/(solid.sp3-solid.sp1)))/C2)[1/psi]");
    model.component("comp1").variable("var1").descr("fail", "\u5931\u6548\u8868\u8fbe\u5f0f");

    model.component("comp1").material().create("pmat1", "PorousMedia");
    model.component("comp1").material("pmat1").set("porosity", "0.3");
    model.component("comp1").material("pmat1").feature().create("fluid1", "Fluid", "comp1");
    model.component("comp1").material("pmat1").feature("fluid1").propertyGroup("def").set("compressibility", "");
    model.component("comp1").material("pmat1").feature("fluid1").propertyGroup("def")
         .set("density", new String[]{"0.0361[lb/in^3]"});
    model.component("comp1").material("pmat1").feature("fluid1").propertyGroup("def")
         .set("compressibility", new String[]{"4e-10[1/Pa]"});
    model.component("comp1").material("pmat1").feature("fluid1").propertyGroup("def")
         .set("dynamicviscosity", new String[]{"1e-7[psi*s]"});
    model.material().create("mat1", "Common", "");
    model.component("comp1").material("pmat1").set("linkBase", "mat1");
    model.material("mat1").label("\u591a\u5b54\u57fa\u4f53");
    model.material("mat1").propertyGroup().create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.material("mat1").propertyGroup("Enu").set("E", new String[]{"4.3e5[psi]"});
    model.material("mat1").propertyGroup("Enu").set("nu", new String[]{"0.16"});
    model.material("mat1").propertyGroup("def").set("density", new String[]{"0.0861[lb/in^3]"});
    model.material("mat1").propertyGroup("def").set("hydraulicpermeability", new String[]{"1e-13[in^2]"});
    model.material("mat1").propertyGroup().create("PoroelasticModel", "PoroelasticModel", "Poroelastic_material");
    model.material("mat1").propertyGroup("PoroelasticModel").set("alphaB", new String[]{"1"});

    model.component("comp1").physics("solid").create("fix1", "Fixed", 2);
    model.component("comp1").physics("solid").feature("fix1").selection().set(1, 3, 13);
    model.component("comp1").physics("solid").create("disp1", "Displacement2", 2);
    model.component("comp1").physics("solid").feature("disp1").selection().set(2, 5);
    model.component("comp1").physics("solid").feature("disp1").setIndex("Direction", "prescribed", 1);
    model.component("comp1").physics("solid").create("disp2", "Displacement2", 2);
    model.component("comp1").physics("solid").feature("disp2").selection().set(4, 11, 12);
    model.component("comp1").physics("solid").feature("disp2").setIndex("Direction", "prescribed", 2);
    model.component("comp1").physics("dl").feature("porous1").feature("fluid1")
         .set("fluidType", "compressibleLinearized");
    model.component("comp1").physics("dl").feature("init1").set("p", "p_r");
    model.component("comp1").physics("dl").create("pr1", "Pressure", 2);
    model.component("comp1").physics("dl").feature("pr1").selection().set(1, 3, 13);
    model.component("comp1").physics("dl").feature("pr1").set("p0", "p_r");
    model.component("comp1").physics("dl").create("pr2", "Pressure", 2);
    model.component("comp1").physics("dl").feature("pr2").selection().set(6, 7, 8, 9, 10);
    model.component("comp1").physics("dl").feature("pr2").set("p0", "p_w");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").label("\u4e95\u8fb9\u754c");
    model.component("comp1").selection("sel1").set(6, 7, 8, 9, 10);

    model.component("comp1").physics("dl").feature("pr2").selection().named("sel1");

    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").run();
    model.result("pg1").label("\u4f4d\u79fb");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").create("def1", "Deform");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").feature("def1").set("scaleactive", true);
    model.result("pg1").feature("surf1").feature("def1").set("scale", 1000);
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").run();
    model.result("pg2").label("\u6d41\u4f53\u538b\u529b\u548c\u901f\u5ea6");
    model.result("pg2").create("iso1", "Isosurface");
    model.result("pg2").feature("iso1").set("expr", "p");
    model.result("pg2").feature("iso1").set("unit", "psi");
    model.result("pg2").feature("iso1").set("colortable", "Cividis");
    model.result("pg2").feature("iso1").set("legendtype", "filled");
    model.result("pg2").run();
    model.result("pg2").create("str1", "Streamline");
    model.result("pg2").feature("str1").set("expr", new String[]{"dl.u", "dl.v", "dl.w"});
    model.result("pg2").feature("str1")
         .set("descr", "\u603b\u8fbe\u897f\u901f\u5ea6\u573a \uff08\u7a7a\u95f4\u5750\u6807\u7cfb\uff09");
    model.result("pg2").feature("str1").set("posmethod", "start");
    model.result("pg2").feature("str1").set("number", 60);
    model.result("pg2").feature("str1").set("linetype", "tube");
    model.result("pg2").feature("str1").set("radiusexpr", "0.4");
    model.result("pg2").feature("str1").create("col1", "Color");
    model.result("pg2").run();
    model.result("pg2").feature("str1").feature("col1").set("expr", "dl.U");
    model.result("pg2").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg2").run();
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "1");
    model.result("pg2").feature("surf1").create("mtrl1", "MaterialAppearance");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").feature("mtrl1").set("appearance", "custom");
    model.result("pg2").feature("surf1").feature("mtrl1").set("family", "steel");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").create("sel1", "Selection");
    model.result("pg2").feature("surf1").feature("sel1").selection().named("sel1");
    model.result("pg2").run();
    model.result("pg2").set("edges", false);
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").run();
    model.result("pg3").label("\u5931\u6548\u51fd\u6570");
    model.result("pg3").create("iso1", "Isosurface");
    model.result("pg3").feature("iso1").set("expr", "fail");
    model.result("pg3").run();
    model.result("pg3").feature("iso1").set("levelmethod", "levels");
    model.result("pg3").feature("iso1").set("levels", "range(-20,10,20)");
    model.result("pg3").feature("iso1").set("colortable", "WaveLight");
    model.result("pg3").run();

    model.title("\u5206\u652f\u4e95\u7684\u7834\u574f");

    model
         .description("\u672c\u4f8b\u57fa\u4e8e\u66fe\u5c31\u804c\u4e8e\u7f8e\u56fd TerraTek \u516c\u53f8\u7684 Roberto Suarez Rivera \u535a\u58eb\u7684\u7814\u7a76\uff0c\u6f14\u793a\u5982\u4f55\u5206\u6790\u88f8\u773c\u4e95\u9644\u8fd1\u7684\u53d8\u5f62\u7834\u574f\u3002\u6211\u4eec\u91c7\u7528\u591a\u5b54\u5f39\u6027\u65b9\u6cd5\u6784\u5efa\u5177\u6709\u56fa\u4f53\u53d8\u5f62\u7684\u591a\u5b54\u4ecb\u8d28\u6d41\u52a8\u6a21\u578b\uff0c\u5e76\u4f7f\u7528\u5e93\u4ed1\u7834\u574f\u8868\u8fbe\u5f0f\u5206\u6790\u574d\u584c\u7684\u53ef\u80fd\u6027\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("multilateral_well.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
