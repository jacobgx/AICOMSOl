/*
 * submarine_cable_07_a_geom_mesh_3d.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:24 by COMSOL 6.3.0.290. */
public class submarine_cable_07_a_geom_mesh_3d {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Tutorials,_Cables");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.param().label("\u51e0\u4f55\u53c2\u6570 1");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("Dcon", "26.2[mm]", "\u4e3b\u5bfc\u4f53\u76f4\u5f84\uff08\u76f8\uff09");
    model.param().set("Tins", "24.0[mm]", "\u7edd\u7f18\u539a\u5ea6 (XLPE)");
    model.param().set("Dins", "77.6[mm]", "\u7edd\u7f18\u76f4\u5f84\uff08XLPE \u548c SCC\uff09");
    model.param().set("Tscc", "(Dins/2-Dcon/2-Tins)/2", "\u534a\u5bfc\u4f53\u5316\u5408\u7269\u539a\u5ea6");
    model.param().set("Tpbs", "2.9[mm]", "\u94c5\u5305\u539a\u5ea6");
    model.param().set("Tpe", "2.9[mm]", "\u805a\u4e59\u70ef\u62a4\u5957\u539a\u5ea6");
    model.param()
         .set("Dpha", "Dins+2*Tpbs+2*Tpe", "\u76f8\u4f4d\u76f4\u5f84\uff08\u5305\u542b\u62a4\u5957\u548c PE\uff09");
    model.param().set("Dpha3", "Dpha*(2/sqrt(3)+1)", "\u4e09\u76f8\u7ed3\u5408\u7684\u76f4\u5f84");
    model.param().set("Dfic", "2.5[mm]", "\u5149\u7ea4\u7ea4\u82af\u76f4\u5f84");
    model.param().set("Tfih", "0.5[mm]", "\u94a2\u87ba\u65cb\u7ebf\u539a\u5ea6\uff08\u5149\u7ea4\uff09");
    model.param().set("Dfib", "9.2[mm]", "\u5149\u7f06\u76f4\u5f84");
    model.param().set("Dcab", "219.0[mm]", "\u6d77\u5e95\u7535\u7f06\u5916\u5f84");
    model.param().set("Darm", "(Dcab+Dpha3)/2", "\u94e0\u88c5\u73af\u4e2d\u5fc3\u76f4\u5f84");
    model.param().set("Tarm", "5.6[mm]", "\u94e0\u88c5\u539a\u5ea6\uff08\u7ebf\u5f84\uff09");
    model.param().set("Narm", "110", "\u73af\u4e2d\u7684\u94e0\u88c5\u7ebf\u6570");
    model.param().set("mfil", "0.5[mm]", "\u586b\u6599\u4f59\u91cf");
    model.param().set("marm", "pi*Darm/Narm-Tarm", "\u94e0\u88c5\u4f59\u91cf");
    model.param().create("par2");
    model.param("par2").label("\u51e0\u4f55\u53c2\u6570 2");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2")
         .set("Acon", "500[mm^2]", "\u4e3b\u5bfc\u4f53\u7684\u6a2a\u622a\u9762\u79ef\uff08\u6bcf\u76f8\uff09");
    model.param("par2")
         .set("Ncon", "Acon/(pi*(Dcon/2)^2)", "\u5bfc\u4f53\u5806\u79ef\u5bc6\u5ea6\uff08\u76f8\uff09");
    model.param("par2")
         .set("Apbs", "pi*(Dins+Tpbs)*Tpbs", "\u94c5\u5305\u7684\u6a2a\u622a\u9762\u79ef\uff08\u6bcf\u76f8\uff09");
    model.param("par2").set("Lsec1", "1/3", "\u4ea4\u53c9\u4e92\u8054\u6bb5\u7684\u76f8\u5bf9\u957f\u5ea6 1");
    model.param("par2")
         .set("Lsec2", "1-Lsec1-Lsec3", "\u4ea4\u53c9\u4e92\u8054\u6bb5\u7684\u76f8\u5bf9\u957f\u5ea6 2");
    model.param("par2").set("Lsec3", "1/3", "\u4ea4\u53c9\u4e92\u8054\u6bb5\u7684\u76f8\u5bf9\u957f\u5ea6 3");
    model.param("par2").set("Lcab", "10[km]", "\u6d77\u5e95\u7535\u7f06\u603b\u957f");
    model.param("par2")
         .set("Scab", "1e5", "\u51e0\u4f55\u6bd4\u4f8b\u56e0\u5b50\uff08\u4e8c\u7ef4\u8f74\u5bf9\u79f0\u6a21\u578b\uff09");
    model.param().create("par3");
    model.param("par3").label("\u51e0\u4f55\u53c2\u6570 3");

//    To import content from file, use:
//    model.param("par3").loadFile("FILENAME");
    model.param("par3")
         .set("LLpha", "18*Dpha3", "\u4e3b\u5bfc\u4f53\u7684\u7ede\u8ddd\uff08\u4e00\u4e2a\u626d\u8f6c\u5468\u671f\uff0c\u987a\u65f6\u9488\uff09");
    model.param("par3")
         .set("LLarm", "-15*Darm", "\u94e0\u88c5\u7ebf\u7684\u7ede\u8ddd\uff08\u4e00\u4e2a\u626d\u8f6c\u5468\u671f\uff0c\u9006\u65f6\u9488\uff09");
    model.param("par3")
         .set("CPcab", "1/(1/LLpha-1/LLarm)", "\u7535\u7f06\u4ea4\u53c9\u8282\u8ddd\uff08\u4e00\u4e2a\u626d\u8f6c\u5468\u671f\uff0c\u76f8\u5bf9\u4e8e\u94e0\u88c5\u7684\u76f8\uff09");
    model.param("par3")
         .set("Nper", "1/10", "\u6a21\u578b\u4e2d\u5305\u542b\u7684\u5468\u671f\u6570\uff08\u8c03\u6574\u53c2\u6570\uff09");
    model.param("par3")
         .set("Tenab", "round(Nper)==Nper", "\u542f\u7528\u6216\u7981\u7528\u626d\u8f6c\uff1a1 \u6216 0\uff08\u5f53 Nper \u4e3a\u6574\u6570\u65f6\u542f\u7528\uff09");
    model.param("par3")
         .set("Lsec", "CPcab*Nper", "\u6a21\u578b\u4e2d\u5305\u542b\u7684\u7535\u7f06\u6bb5\u957f\u5ea6");
    model.param("par3")
         .set("Tsec", "360[deg]*Tenab*Lsec/LLpha", "\u6a21\u578b\u4e2d\u5305\u542b\u7684\u7535\u7f06\u6bb5\u626d\u8f6c\u89d2\u5ea6");
    model.param("par3")
         .set("SCFpha", "if(Tenab,sqrt((2*pi*Dpha/sqrt(3))^2+LLpha^2)/abs(LLpha),1)", "\u503e\u659c\u6821\u6b63\u56e0\u5b50\uff0c\u4e3b\u5bfc\u4f53\uff08\u5f53 Nper \u4e3a\u6574\u6570\u65f6\u542f\u7528\uff09");
    model.param("par3")
         .set("SCFarm", "if(Tenab,sqrt((2*pi*Darm/2)^2+LLarm^2)/abs(LLarm),1)", "\u503e\u659c\u6821\u6b63\u56e0\u5b50\uff0c\u94e0\u88c5\u7ebf\uff08\u5f53 Nper \u4e3a\u6574\u6570\u65f6\u542f\u7528\uff09");
    model.param("par3")
         .set("TCFp20", "sqrt(pi/(5*(sqrt(5)-1)/2))", "\u622a\u65ad\u6821\u6b63\u56e0\u5b50\uff0c\u4e8c\u5341\u8fb9\u5f62\uff08\u76f8\uff09");
    model.param("par3")
         .set("TCFp16", "sqrt(pi/(4*sqrt(2-sqrt(2))))", "\u622a\u65ad\u6821\u6b63\u56e0\u5b50\uff0c\u5341\u516d\u8fb9\u5f62\uff08\u94e0\u88c5\uff09");

    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "Darm/2+2*Tarm/3");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "Lsec");
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new String[]{"0", "0", "-Lsec/2"});
    model.component("comp1").geom("geom1").feature("cyl1").setIndex("layer", "4*Tarm/3", 0);
    model.component("comp1").geom("geom1").run("cyl1");

    model.component("comp1").view("view1").set("showgrid", false);
    model.component("comp1").view("view1").set("transparency", true);
    model.component("comp1").view("view1").label("\u89c6\u56fe 1\uff08\u6b63\u4ea4\uff09");
    model.component("comp1").view("view1").set("locked", true);
    model.component("comp1").view("view1").set("showmaterial", true);
    model.component("comp1").view("view1").camera().set("viewscaletype", "manual");
    model.component("comp1").view("view1").camera().set("zscale", "1/(10*Nper)");

    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"Darm/2", "Darm/2", "1"});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("size", "Darm/2", 2);
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new String[]{"0", "0", "Lsec/2-Darm/2"});
    model.component("comp1").geom("geom1").run("blk1");

    model.component("comp1").view().duplicate("view2", "view1");
    model.component("comp1").view("view2").label("\u89c6\u56fe 2\uff08\u6b63\u4ea4\uff0c\u4fef\u89c6\u56fe\uff09");
    model.component("comp1").view("view2").set("locked", true);
    model.component("comp1").view().duplicate("view3", "view2");
    model.component("comp1").view("view3").label("\u89c6\u56fe 3\uff08\u6b63\u4ea4\uff0c\u4ef0\u89c6\u56fe\uff09");
    model.component("comp1").view("view3").set("locked", true);
    model.component("comp1").view().duplicate("view4", "view3");
    model.component("comp1").view("view4").label("\u89c6\u56fe 4\uff08\u6b63\u4ea4\uff0c\u4fa7\u89c6\u56fe\uff09");
    model.component("comp1").view("view4").set("showgrid", true);
    model.component("comp1").view("view4").set("locked", false);
    model.component("comp1").view("view4").set("showgrid", false);
    model.component("comp1").view("view4").set("locked", true);
    model.component("comp1").view().duplicate("view5", "view4");
    model.component("comp1").view("view5").label("\u89c6\u56fe 5\uff08\u900f\u89c6\u56fe\uff09");
    model.component("comp1").view("view5").camera().set("projection", "perspective");
    model.component("comp1").view("view5").camera().set("zoomanglefull", 36);
    model.component("comp1").view("view5").camera().setIndex("position", "1.4*Dcab", 0);
    model.component("comp1").view("view5").camera().set("position", new String[]{"1.4*Dcab", "0.4*Dcab", "3"});
    model.component("comp1").view("view5").camera().setIndex("position", "Lsec/(2*Nper)+2.8*Dcab", 2);
    model.component("comp1").view("view5").camera().setIndex("up", "1e-6", 0);
    model.component("comp1").view("view5").camera().setIndex("up", 0, 0);
    model.component("comp1").view("view5").camera().set("up", new int[]{0, 1, 0});
    model.component("comp1").view("view5").camera().set("zscale", "1/Nper");
    model.component("comp1").view("view5").camera().set("viewoffset", new String[]{"0.8*Dcab", "0.8*Dcab"});

    model.param("par3").set("Nper", "1");

    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").view("view1").set("locked", false);
    model.component("comp1").view("view2").set("locked", false);
    model.component("comp1").view("view3").set("locked", false);
    model.component("comp1").view("view4").set("locked", false);
    model.component("comp1").view("view5").set("locked", false);
    model.component("comp1").view("view1").camera().set("projection", "orthographic");
    model.component("comp1").view("view1").camera().set("orthoscale", 0.5474655628204346);
    model.component("comp1").view("view2").camera().set("projection", "orthographic");
    model.component("comp1").view("view2").camera().set("orthoscale", 0.3977237939834595);
    model.component("comp1").view("view3").camera().set("projection", "orthographic");
    model.component("comp1").view("view3").camera().set("orthoscale", 0.3977237939834595);
    model.component("comp1").view("view4").camera().set("projection", "orthographic");
    model.component("comp1").view("view4").camera().set("orthoscale", 0.3555237948894501);

    model.title("\u6d77\u5e95\u7535\u7f06 7a - \u51e0\u4f55\u548c\u7f51\u683c\u4e09\u7ef4\u6a21\u578b");

    model
         .description("\u8fd9\u4e2a MPH \u6587\u4ef6\u8868\u793a\u5728\u201c\u51e0\u4f55\u548c\u7f51\u683c\u4e09\u7ef4\u6a21\u578b\u201d\u6559\u7a0b\u4e2d\u6784\u5efa\u548c\u5206\u6790\u7684\u6a21\u578b\u7684\u4e2d\u95f4\u72b6\u6001\u3002\u6709\u5173\u66f4\u591a\u4fe1\u606f\uff0c\u5305\u62ec\u8be6\u7ec6\u7684\u4ecb\u7ecd\u3001\u5206\u6b65\u64cd\u4f5c\u8bf4\u660e\u548c\u7ed3\u679c\u5206\u6790\uff08\u5305\u542b\u9a8c\u8bc1\u4ee5\u53ca\u5bf9\u5b66\u672f\u7814\u7a76\u7684\u5f15\u7528\uff09\uff0c\u8bf7\u53c2\u9605\u201c\u6848\u4f8b\u5e93\u201d\u4e2d\u672c\u6559\u7a0b\u7684\u4e3b\u5165\u53e3\u70b9\u9644\u5e26\u7684 *.pdf \u6587\u4ef6\uff1asubmarine_cable_07_geom_mesh_3d\u3002");

    model.mesh().clearMeshes();

    model.label("submarine_cable_07_a_geom_mesh_3d.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
