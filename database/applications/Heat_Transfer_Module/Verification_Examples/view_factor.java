/*
 * view_factor.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:38 by COMSOL 6.3.0.290. */
public class view_factor {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Heat_Transfer_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("rad", "SurfaceToSurfaceRadiation", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/rad", true);

    model.param().set("r_int", "0.3[m]");
    model.param().descr("r_int", "\u5185\u7403\u4f53\u534a\u5f84");
    model.param().set("r_ext", "1[m]");
    model.param().descr("r_ext", "\u5916\u7403\u4f53\u534a\u5f84");
    model.param().set("F_ext_ext", "1-(r_int/r_ext)^2");
    model.param()
         .descr("F_ext_ext", "\u4ece\u5916\u7403\u4f53\u5230\u5916\u7403\u4f53\u7684\u89e3\u6790\u89c6\u89d2\u7cfb\u6570");
    model.param().set("F_ext_int", "(r_int/r_ext)^2");
    model.param()
         .descr("F_ext_int", "\u4ece\u5916\u7403\u4f53\u5230\u5185\u7403\u4f53\u7684\u89e3\u6790\u89c6\u89d2\u7cfb\u6570");
    model.param().set("F_int_int", "0");
    model.param()
         .descr("F_int_int", "\u4ece\u5185\u7403\u4f53\u5230\u5185\u7403\u4f53\u7684\u89e3\u6790\u89c6\u89d2\u7cfb\u6570");
    model.param().set("F_int_ext", "1");
    model.param()
         .descr("F_int_ext", "\u4ece\u5185\u7403\u4f53\u5230\u5916\u7403\u4f53\u7684\u89e3\u6790\u89c6\u89d2\u7cfb\u6570");

    model.component("comp1").geom("geom1").create("sph1", "Sphere");
    model.component("comp1").geom("geom1").feature("sph1").set("type", "surface");
    model.component("comp1").geom("geom1").feature("sph1").set("r", "r_int");
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("\u5185\u7403\u4f53");
    model.component("comp1").geom("geom1").feature("sph1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("sph1");
    model.component("comp1").geom("geom1").create("sph2", "Sphere");
    model.component("comp1").geom("geom1").feature("sph2").set("type", "surface");
    model.component("comp1").geom("geom1").feature("sph2").set("r", "r_ext");
    model.component("comp1").geom("geom1").selection().create("csel2", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel2").label("\u5916\u7403\u4f53");
    model.component("comp1").geom("geom1").feature("sph2").set("contributeto", "csel2");
    model.component("comp1").geom("geom1").run("sph2");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("sph1", 1, 2, 3, 4, 5, 6, 7);
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("sph2", 1, 2, 3, 4, 5, 6, 7);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u9ed1\u4f53");
    model.component("comp1").material("mat1").propertyGroup("def").set("emissivity", new String[]{"1"});

    model.component("comp1").physics("rad").feature("dsurf1").set("radDirectionType", "RadiationDirectionPlus");
    model.component("comp1").physics("rad").create("dsurf2", "DiffuseSurface", 2);
    model.component("comp1").physics("rad").feature("dsurf2").selection().named("geom1_csel2_bnd");
    model.component("comp1").physics("rad").feature("dsurf2").set("radDirectionType", "RadiationDirectionMinus");
    model.component("comp1").physics("rad").create("rsym1", "SymmetryForSurfaceToSurfaceRadiation", -1);
    model.component("comp1").physics("rad").feature("rsym1")
         .set("typeOfSymmetry", "ThreePerpendicularSymmetryPlanes");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").label("\u6307\u793a\u5668\uff0c\u5185\u7403\u4f53");
    model.component("comp1").variable("var1").selection().geom("geom1", 2);
    model.component("comp1").variable("var1").selection().named("geom1_csel1_bnd");
    model.component("comp1").variable("var1").set("ext", "0");
    model.component("comp1").variable("var1").descr("ext", "\u5916\u8868\u9762\u6307\u793a\u5668");
    model.component("comp1").variable("var1").set("int", "1");
    model.component("comp1").variable("var1").descr("int", "\u5185\u8868\u9762\u6307\u793a\u5668");
    model.component("comp1").variable().create("var2");
    model.component("comp1").variable("var2").label("\u6307\u793a\u5668\uff0c\u5916\u7403\u4f53");
    model.component("comp1").variable("var2").selection().geom("geom1", 2);
    model.component("comp1").variable("var2").selection().named("geom1_csel2_bnd");
    model.component("comp1").variable("var2").set("ext", "1", "\u5916\u8868\u9762\u6307\u793a\u5668");
    model.component("comp1").variable("var2").set("int", "0", "\u5185\u8868\u9762\u6307\u793a\u5668");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").label("\u79ef\u5206\uff0c\u5185\u7403\u4f53");
    model.component("comp1").cpl("intop1").set("opname", "intop_int");
    model.component("comp1").cpl("intop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop1").selection().named("geom1_csel1_bnd");
    model.component("comp1").cpl().create("intop2", "Integration");
    model.component("comp1").cpl("intop2").set("axisym", true);
    model.component("comp1").cpl("intop2").label("\u79ef\u5206\uff0c\u5916\u7403\u4f53");
    model.component("comp1").cpl("intop2").set("opname", "intop_ext");
    model.component("comp1").cpl("intop2").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop2").selection().named("geom1_csel2_bnd");

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().all();
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().named("geom1_csel1_bnd");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", "r_int/5");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").selection().named("geom1_csel2_bnd");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("hmax", "r_ext/5");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("sol");
    model.study("std1").createAutoSequences("jobs");

    model.sol("sol1").runFromTo("st1", "v1");

    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").label("\u89c6\u89d2\u7cfb\u6570");
    model.result().numerical("gev1").setIndex("expr", "intop_int(rad.radopu(int,0))/intop_int(1)", 0);
    model.result().numerical("gev1").setIndex("unit", 1, 0);
    model.result().numerical("gev1").setIndex("descr", "\u5185\u90e8\u5230\u5185\u90e8\u89c6\u89d2\u7cfb\u6570", 0);
    model.result().numerical("gev1").setIndex("expr", "intop_ext(rad.radopd(int,0))/intop_int(1)", 1);
    model.result().numerical("gev1").setIndex("unit", 1, 1);
    model.result().numerical("gev1").setIndex("descr", "\u5185\u90e8\u5230\u5916\u90e8\u89c6\u89d2\u7cfb\u6570", 1);
    model.result().numerical("gev1").setIndex("expr", "intop_ext(rad.radopd(0,ext))/intop_ext(1)", 2);
    model.result().numerical("gev1").setIndex("unit", 1, 2);
    model.result().numerical("gev1").setIndex("descr", "\u5916\u90e8\u5230\u5916\u90e8\u89c6\u89d2\u7cfb\u6570", 2);
    model.result().numerical("gev1").setIndex("expr", "intop_int(rad.radopu(0,ext))/intop_ext(1)", 3);
    model.result().numerical("gev1").setIndex("unit", 1, 3);
    model.result().numerical("gev1").setIndex("descr", "\u5916\u90e8\u5230\u5185\u90e8\u89c6\u89d2\u7cfb\u6570", 3);
    model.result().numerical().create("gev2", "EvalGlobal");
    model.result().numerical("gev2").label("\u7edd\u5bf9\u8bef\u5dee\uff0c\u89c6\u89d2\u7cfb\u6570");
    model.result().numerical("gev2").setIndex("expr", "abs(intop_int(rad.radopu(int,0))/intop_int(1)-F_int_int)", 0);
    model.result().numerical("gev2").setIndex("unit", 1, 0);
    model.result().numerical("gev2")
         .setIndex("descr", "\u7edd\u5bf9\u8bef\u5dee\uff0c\u5185\u90e8\u5230\u5185\u90e8\u89c6\u89d2\u7cfb\u6570", 0);
    model.result().numerical("gev2").setIndex("expr", "abs(intop_ext(rad.radopd(int,0))/intop_int(1)-F_int_ext)", 1);
    model.result().numerical("gev2").setIndex("unit", 1, 1);
    model.result().numerical("gev2")
         .setIndex("descr", "\u7edd\u5bf9\u8bef\u5dee\uff0c\u5185\u90e8\u5230\u5916\u90e8\u89c6\u89d2\u7cfb\u6570", 1);
    model.result().numerical("gev2").setIndex("expr", "abs(intop_ext(rad.radopd(0,ext))/intop_ext(1)-F_ext_ext)", 2);
    model.result().numerical("gev2").setIndex("unit", 1, 2);
    model.result().numerical("gev2")
         .setIndex("descr", "\u7edd\u5bf9\u8bef\u5dee\uff0c\u5916\u90e8\u5230\u5916\u90e8\u89c6\u89d2\u7cfb\u6570", 2);
    model.result().numerical("gev2").setIndex("expr", "abs(intop_int(rad.radopu(0,ext))/intop_ext(1)-F_ext_int)", 3);
    model.result().numerical("gev2").setIndex("unit", 1, 3);
    model.result().numerical("gev2")
         .setIndex("descr", "\u7edd\u5bf9\u8bef\u5dee\uff0c\u5916\u90e8\u5230\u5185\u90e8\u89c6\u89d2\u7cfb\u6570", 3);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u89c6\u89d2\u7cfb\u6570");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("\u7edd\u5bf9\u8bef\u5dee\uff0c\u89c6\u89d2\u7cfb\u6570");
    model.result().numerical("gev2").set("table", "tbl2");
    model.result().numerical("gev2").setResult();

    model.component("comp1").geom("geom1").feature("del1").active(false);
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").view().create("view2", "geom1");
    model.component("comp1").view("view2").hideObjects().create("hide1");
    model.component("comp1").view("view2").hideObjects("hide1").init(2);
    model.component("comp1").view("view2").hideObjects("hide1").set("sph2", 2);

    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("rad").feature("rsym1").active(false);

    model.study("std1").createAutoSequences("sol");
    model.study("std1").createAutoSequences("jobs");

    model.sol("sol1").runFromTo("st1", "v1");

    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").appendResult();
    model.result().numerical("gev2").set("table", "tbl2");
    model.result().numerical("gev2").appendResult();

    model.title("\u89d2\u7cfb\u6570\u8ba1\u7b97");

    model
         .description("\u8fd9\u4e2a\u57fa\u51c6\u6a21\u578b\u6f14\u793a\u5982\u4f55\u8ba1\u7b97\u4e24\u4e2a\u540c\u5fc3\u5706\u7403\u5f7c\u6b64\u76f8\u4e92\u8f90\u5c04\u65f6\u7684\u51e0\u4f55\u89d2\u7cfb\u6570\u3002\u4eff\u771f\u7ed3\u679c\u4e0e\u7cbe\u786e\u7684\u89e3\u6790\u503c\u8fdb\u884c\u4e86\u6bd4\u8f83\u3002");

    model.label("view_factor.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
